package org.mbari.odss.services.planning

import scala.collection.mutable
import scala.List
import org.mbari.odss.services.planning.trex.VarItem


sealed abstract class BaseObject

case class TimelineBrief(name:         String,
                         alive:        Boolean,
                         accept_goals: Boolean,
                         publish_plan: Boolean,
                         total_obs:    Int)

case class Timelines(timelines: List[TimelineBrief]) extends BaseObject


case class TDate(value: Option[String] = None, min: Option[String] = None, max: Option[String] = None) extends BaseObject

case class TDuration(value: Option[String], min: Option[String], max: Option[String]) extends BaseObject

case class Token(start:      Option[TDate],
                 end:        Option[TDate],
                 duration:   Option[TDuration],
                 text:       String,
                 section_id: String,
                 status:     String,
                 tid:        String,
                 vars:       List[VarItem]
                ) extends BaseObject

//case class Token(early_start: String, late_start: String,
//                 early_end: String, late_end: String,
//                 text: String, section_id: String, status: String, tid: String,
//                 duration: Option[TDuration] = None
//                  )  extends BaseObject
//


case class Timeline(name: String,
                    tokens: List[Token]) extends BaseObject




/////////////////////////////////
// TODO remove all the following

case class Section(key: Int, label: String)

object SectionData {
  val all = mutable.MutableList[Section](
    Section(1, "ESPs"),
    Section(2, "LRAUV Tethys"),
    Section(3, "LRAUV Daphne"),
    Section(4, "Rachel Carson"),
    Section(5, "AUV Dorado")
  )
}

// while using the data.
case class Event(start_date: String, end_date: String, text: String, section_id: Int)

object SchedulerData {
  val all = mutable.MutableList[Event](
    Event("2013-03-08 00:00", "2013-04-05 23:00", "X", 1),
    Event("2013-03-10 00:00", "2013-03-11 23:00", "T", 2),
    Event("2013-03-12 00:00", "2013-03-12 23:00", "D", 2),
    Event("2013-03-13 00:00", "2013-03-18 23:00", "X", 2),
    Event("2013-03-19 00:00", "2013-03-19 23:00", "R", 2),
    Event("2013-03-20 00:00", "2013-03-20 23:00", "D", 2),
    Event("2013-03-21 00:00", "2013-03-27 23:00", "X", 2),
    Event("2013-03-28 00:00", "2013-03-28 23:00", "R", 2),
    Event("2013-03-29 00:00", "2013-03-29 23:00", "T", 2),
    Event("2013-03-10 00:00", "2013-03-11 23:00", "T", 3),
    Event("2013-03-12 00:00", "2013-03-12 23:00", "D", 3),
    Event("2013-03-13 00:00", "2013-03-18 23:00", "X", 3),
    Event("2013-03-19 00:00", "2013-03-19 23:00", "R", 3),
    Event("2013-03-20 00:00", "2013-03-20 23:00", "D", 3),
    Event("2013-03-21 00:00", "2013-03-27 23:00", "X", 3),
    Event("2013-03-28 00:00", "2013-03-28 23:00", "R", 3),
    Event("2013-03-29 00:00", "2013-03-29 23:00", "T", 3),
    Event("2013-03-04 00:00", "2013-03-05 23:00", "T", 4),
    Event("2013-03-15 00:00", "2013-03-17 23:00", "X", 4),
    Event("2013-03-20 00:00", "2013-03-22 23:00", "X", 4),
    Event("2013-03-15 00:00", "2013-03-17 23:00", "X", 5),
    Event("2013-03-20 00:00", "2013-03-22 23:00", "X", 5)
  )
}
