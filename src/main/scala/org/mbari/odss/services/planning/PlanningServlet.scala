package org.mbari.odss.services.planning


import com.github.nscala_time.time.Imports._
import scala.collection.mutable

import com.typesafe.scalalogging.slf4j.Logging
import org.mbari.odss.services.planning.trex.{VarItem, TrexClient}


/**
 * The service that supports the Autonomy UI planning component.
 *
 */
class PlanningServlet(trex: TrexClient) extends PlanningServiceStack with Logging {

  //////////////////////////////////////////////////////////////
  // timelines

  get("/timelines") {
    logger.info("/timelines called.")
    val tTimelines = trex.timelines
    val res = tTimelines.timelines map { e =>
      TimelineBrief(e.name, e.alive, e.accept_goals, e.publish_plan, e.total_obs)}
    res
  }

  get("/timeline/:name") {
    val name = params("name")

    def getTDate(varItems: List[VarItem], name: String) = {
      val varItem: Option[VarItem] = (varItems filter (_.name == name)).headOption

      def fixUtc(str: Option[String]): Option[String] = {
        str map { s => if (! (s endsWith "Z")) s + "Z" else s }
      }

      val res = varItem map { x =>
        TDate(fixUtc(x.map get "value"),
              fixUtc(x.map get "min"),
              fixUtc(x.map get "max"))
      }

      res
    }

    def getTDuration(varItems: List[VarItem], name: String) = {
      val varItem: Option[VarItem] = (varItems filter (_.name == name)).headOption

      val res = varItem map { x =>
        TDuration(x.map get "value",
              x.map get "min",
              x.map get "max")
      }

      res
    }

    val tTimeline = trex.timeline(name)

    val fromTrex = tTimeline.tokens map { t =>
      val varItems: List[VarItem] = t.Variable

      val start    = getTDate(varItems, "start")
      val end      = getTDate(varItems, "end")
      val duration = getTDuration(varItems, "duration")

      val text        = t.pred
      val section_id  = t.on
      val status      = TokenStatus.Accepted.toString
      val tid         = newTid

      Token(start, end, duration, text, section_id, status, tid)

    }

    val drafts = {
      val tokens = TokenData.all filter (_.section_id == name)
      tokens
    }

    fromTrex ++ drafts
  }

  get("/tick/rate") {
    trex.tickRate
  }

  get("/tick/next") {
    trex.tickNext
  }


  //////////////////////////////////////////////////////////////
  // tokens

  get("/tokens") {
    // get the overlapping events:
    val from: DateTime = if (params.contains("from")) params.get("from").get else minDate
    val to:   DateTime = if (params.contains("to"))   params.get("to").get   else maxDate

    // do not restrict to the given period just yet
    val tokens = TokenData.all // filter (e => from <= e.early_start && to >= e.late_end)

    // get the sections referenced in the selected events:
    //val sections = SectionData.all filter (s => (tokens exists (_.section_id == s.key)))
    // no; for now, returning all sections:
    val sections = SectionData.all

    Map("sections" -> sections, "tokens" -> tokens)
  }

  //////////////////////////////////////////////////////////////
  // submit goal

  post("/goal") {

    logger.info(s"goal: params = $params")

    val early_start = params("early_start")
    val late_start  = params("late_start")
    val early_end   = params("early_end")
    val late_end    = params("late_end")
    val text        = params("text")
    val section     = params("section")
    val tidOpt      = params.get("tid")

    tidOpt foreach { tid =>
      // a tid previously associated.
      // remove entry from the data for that obsolete tid:
      val idx = TokenData.all.indexWhere(_.tid == tid)
      if (idx >= 0) {
        TokenData.all.remove(idx)
        logger.info(s"removed token with tid=$tid")
      }
    }

    val start    = Some(TDate(min=Some(early_start), max=Some(late_start)))
    val end      = Some(TDate(min=Some(early_end),   max=Some(late_end)))
    val duration = Some(TDuration(Some("20:00"), None, None))

    val goal = Token(start, end, duration, text, section, TokenStatus.Pending, newTid)

    TokenData.all += goal
    logger.info(s"added goal token with tid=${goal.tid}")
    goal
  }

  get("/goals") {
    TokenData.all filter (_.status == TokenStatus.Pending.toString)
  }

  // developer conveniences

  get("/remove_pending") {
    val n = TokenData.all.length
    val toRemove = TokenData.all filter (_.status == TokenStatus.Pending.toString)
    toRemove foreach { TokenData.all -= _ }
    val res = "Removed " + (n - TokenData.all.length) + " tokens"
    logger.info(res)
    res
  }

  //////////////////////////////////////////////////////////////
  // save draft goals

  post("/draft_goals") {

    logger.info("draft_goals: params=" + params)

    val early_start = params("early_start")
    val late_start  = params("late_start")
    val early_end   = params("early_end")
    val late_end    = params("late_end")

    val text        = params("text")
    val section     = params("section")
    val tidOpt      = params.get("tid")

    val testOpt      = params.get("test")
    val testFooOpt      = params.get("test[foo]")

    logger.info(s"draft_goals: testOpt=$testOpt")
    logger.info(s"draft_goals: testFooOpt=$testFooOpt")

    tidOpt foreach { tid =>
      // a tid previously associated. Remove entry from the data for that obsolete tid:
      val idx = TokenData.all.indexWhere(_.tid == tid)
      if (idx >= 0) {
        TokenData.all.remove(idx)
        logger.info(s"removed token with tid=$tid")
      }
    }

    val start    = Some(TDate(min=Some(early_start), max=Some(late_start)))
    val end      = Some(TDate(min=Some(early_end),   max=Some(late_end)))
    val duration = None

    val goal = Token(start, end, duration, text, section, TokenStatus.NewSaved, newTid)

    TokenData.all += goal
    logger.info(s"added draft: $goal")
    goal
  }

  get("/draft_goals") {
    TokenData.all filter (_.status == TokenStatus.New.toString)
  }

  get("/remove_draft") {
    val n = TokenData.all.length
    val toRemove = TokenData.all filter (e => e.status == TokenStatus.New.toString || e.status == TokenStatus.NewSaved.toString)
    toRemove foreach { TokenData.all -= _ }
    val res = "Removed " + (n - TokenData.all.length) + " tokens"
    logger.info(res)
    res
  }

  get("/remove_all") {
    val n = TokenData.all.length
    TokenData.all.clear()
    val res = "Removed " + (n - TokenData.all.length) + " tokens"
    logger.info(res)
    res
  }

  // TODO remove
  // convenience for immediate testing of UI scheduler component
  get("/scheduler") {
    // get the overlapping events:
    val from: DateTime = if (params.contains("from")) params.get("from").get else minDate
    val to:   DateTime = if (params.contains("to"))   params.get("to").get else maxDate

    val events = SchedulerData.all filter (e => from <= e.end_date && to >= e.start_date)

    // get the sections referenced in the selected events:
    val sections = SectionData.all filter (s => (events exists (_.section_id == s.key)))

    Map("sections" -> sections, "events" -> events)
  }

}

object TokenData {
  val all = mutable.ArrayBuffer[Token]()
}


