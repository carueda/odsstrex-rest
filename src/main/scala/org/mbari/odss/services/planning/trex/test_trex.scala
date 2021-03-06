package org.mbari.odss.services.planning.trex

import org.joda.time.DateTime

object test_trex extends App {

  val trex = new TrexClient("http://threadfish.shore.mbari.org:2222/rest/")

  println("version = " + trex.version)
  println(prettyFormat(trex.help))

  println("tick        = " + trex.tick)
  println("tickInitial = " + trex.tickInitial)
  println("tickFinal   = " + trex.tickFinal)
  println("tickNext    = " + trex.tickNext)

  val now = DateTime.now()
  println("tickAt(" +now+ ")  = " + trex.tickAt(now.toString))
  println("tickRate    = " + trex.tickRate)


  import scala.concurrent.ExecutionContext.Implicits.global
  val fut = trex.tickWait
  val startTime = System.currentTimeMillis()
  println("tickWait: waiting for response: " + fut.getClass)
  for (t <- fut) {
    val delay = System.currentTimeMillis() - startTime
    println("Got tickWait response: " +t+ "  (took " + delay+ " ms)")
  }

  println("timelines:")
  val timelines = trex.timelines
  println(prettyFormat(timelines))

  val timelineName = "dorado_survey"
  val from = Some(now)
  val to   = Some(now.plusDays(10))
  println("timeline(" +timelineName+ ", from=" +now+ ", to=" +to+ "):")
  if (timelines.timelines exists (_.name == timelineName)) {
    val timeline = trex.timeline(timelineName, from, to)
    println(prettyFormat(timeline))
  }
  else println("(" +timelineName+ ": not contained in reported timelines)")

  println("goals:")
  val goals = trex.goals
  println(prettyFormat(goals))
  if (!goals.goals.isEmpty) {
    val goalId = goals.goals(0).id
    println("goal(" +goalId+ "):")
    println(prettyFormat(trex.goal(goalId)))
  }

}
