package org.mbari.odss.services.planning.trex

import dispatch._, Defaults._
import com.ning.http.client
import org.json4s._
import org.json4s.native.JsonMethods._
import com.typesafe.scalalogging.slf4j.Logging
import org.joda.time.DateTime

private case class GoalWithJson(on: String, pred: String, Variable: JArray)
private case class GoalEntryWithJson(id: String, href: String, Goal: GoalWithJson)
private case class GoalsWithJson(goals: List[GoalEntryWithJson])

private case class TokenWithJson(on: String, pred: String, Variable: JArray)
private case class TimelineWithJsonTokens(name: String,
                                          requested_tick_range: JValue,
                                          tokens: List[TokenWithJson])

/**
 * Client to the T-REX endpoint.
 *
 * @param endpoint   Base URL for the endpoint.
 */
class TrexClient(endpoint: String) extends AnyRef with Logging {

  private[this] val baseRest = if (endpoint.endsWith("/")) endpoint else endpoint + "/"
  logger.info(s"baseRest: $baseRest")

  implicit val formats = DefaultFormats // Brings in default date formats etc.

  object read {
    object Tick extends (client.Response => Tick) {
      def apply(r: client.Response) = (as.String andThen (parse(_)))(r).extract[Tick]
    }

    object TickRate extends (client.Response => TickRate) {
      def apply(r: client.Response) = (as.String andThen (parse(_)))(r).extract[TickRate]
    }

    object Json extends (client.Response => JValue) {
      def apply(r: client.Response) = (as.String andThen (parse(_)))(r)
    }
  }

  private def jObj2VarItem(jo: JObject): VarItem = {

    val name  = jo.values("name").toString
    val type_ = jo.values("type").toString
    val map   = jo.values(type_).asInstanceOf[Map[String,String]]

    VarItem(name, type_, map)

//    type_ match {
//      case "enum"     => VarEnum( name, map)
//      case "bool"     => VarBool( name, map)
//      case "float"    => VarFloat(name, map)
//      case "int"      => VarInt(  name, map)
//      case "date"     => VarDate( name, map)
//      case "duration" => VarDur(  name, map)
//      case _          => {assert(assertion=false, "unexpected type: " +type_ + ". jo=" + jo); VarNull}
//    }
  }

  private def goalWithJson2VarItem(gj: JValue): VarItem = {
    assert(gj.isInstanceOf[JObject])
    val jo = gj.asInstanceOf[JObject]
    jObj2VarItem(jo)
  }

  private def parseGoalEntry(json: GoalEntryWithJson) = {
    val x = json
    val varItems = x.Goal.Variable.arr map (y => goalWithJson2VarItem(y))
    GoalEntry(x.id, x.href, x.Goal.on, x.Goal.pred, varItems)
  }

  private def parseGoals(json: GoalsWithJson) = {
    val goals = json.goals map (x => {
      val varItems = x.Goal.Variable.arr map (y => goalWithJson2VarItem(y))
      GoalEntry(x.id, x.href, x.Goal.on, x.Goal.pred, varItems)
    })
    Goals(goals)
  }

  private def jObj2RequestedTickRange(gj: JValue): Option[RequestedTickRange] = {
    assert(gj.isInstanceOf[JObject])
    val jo = gj.asInstanceOf[JObject]
    if (jo.values contains "int") {
      val map = jo.values("int").asInstanceOf[Map[String,Any]]
      val min = map.get("min") map (s => Integer.parseInt(s.toString))
      val max = map.get("max") map (s => Integer.parseInt(s.toString))
      Option(RequestedTickRange(min, max))
    }
    else {
      None
    }
  }

  private def parseTimelineTokens(json: TimelineWithJsonTokens) = {
    val tokens = json.tokens map (x => {
      val varItems = x.Variable.arr map (y => goalWithJson2VarItem(y))
      Token(x.on, x.pred, varItems)
    })
    val requested_tick_range = jObj2RequestedTickRange(json.requested_tick_range)
    Timeline(json.name, requested_tick_range, tokens)
  }

  def help = {
    val svc = url(baseRest + "help")
    val res = Http(svc OK as.String)
    val json = parse(res())
    json.extract[Help]
  }

  def version = {
    val svc = url(baseRest + "version")
    val res = Http(svc OK as.String)
    res()
  }

  private def _tick(sub: String = "") = {
    val svc = url(baseRest + "tick" + sub)
    val res = Http(svc OK read.Tick)
    res()
  }

  def tick        = _tick()
  def tickInitial = _tick("/initial")
  def tickFinal   = _tick("/final")
  def tickNext    = _tick("/next")
  def tickAt(at: String) = _tick("/at/" + UriEncode.path(at))

  def tickRate = {
    val svc = url(baseRest + "tick/rate")
    val res = Http(svc OK read.TickRate)
    res()
  }

  /**
   * Note, this one returns a Future
   * @return a Future[x]
   */
  def tickWait: Future[Tick] = {
    val svc = url(baseRest + "tick/wait")
    val res = Http(svc OK read.Tick)
    res
  }

  def timelines = {
    val svc = url(baseRest + "timelines")
    val res = Http(svc OK as.String)
    val json = parse(res())
    json.extract[Timelines]
  }

  def timeline(name: String, from: Option[DateTime] = None, to: Option[DateTime] = None) = {
    val svc = url(baseRest + "timeline/" + name)
  //val svc = url(baseRest + "timeline/" + name + "?from=2013-06-12T12:00+08:00")
    from foreach (f => svc.addQueryParameter("from", f.toString))
    to   foreach (t => svc.addQueryParameter("to",   t.toString))
    logger.info(s"GET: ${svc.build().getRawUrl}")
    val res = Http(svc OK as.String)
    val str =  res()
    val json = parse(str)
    val timelineWithJson = json.extract[TimelineWithJsonTokens]

    parseTimelineTokens(timelineWithJson)
  }

  /**
   * POSTs a goal
   * @param data
   * @return
   */
  def postGoal(data: Map[String, String]) = {
    logger.info(s"POST $data")
    val svc = url(baseRest + "goal")
    svc << data
  }

  def goals = {
    val svc = url(baseRest + "goals")
    val res = Http(svc OK as.String)
    val json = parse(res())
    val goalsWithJson = json.extract[GoalsWithJson]

//    logger.info(s"goalsWithJson = $goalsWithJson")

    parseGoals(goalsWithJson)
  }

  def goal(id: String) = {
    val svc = url(baseRest + "goal/" + id)
    val res = Http(svc OK as.String)
    val json = parse(res())
    val goalEntryWithJson = json.extract[GoalEntryWithJson]

//    logger.info(s"goalEntryWithJson = $goalEntryWithJson")

    parseGoalEntry(goalEntryWithJson)
  }
}
