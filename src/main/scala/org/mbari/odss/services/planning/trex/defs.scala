package org.mbari.odss.services.planning.trex

import org.json4s.JsonAST.JValue


// TODO dates as DateTimes (instead of strings)

sealed abstract class BaseObject

case class HelpItem(href: String, info: String) extends BaseObject

case class Help(help: List[HelpItem])           extends BaseObject

case class Tick(value: String, date: String)    extends BaseObject

case class TickRate(nanoseconds: String, duration: String)    extends BaseObject

case class TimelineBrief(name: String,
                         href: String,
                         alive: String,
                         accept_goals: String,
                         latency: JValue,
                         look_ahead: JValue,
                         publish_plan: String,
                         total_obs: Int,
                         obs_period: JValue
                          ) extends BaseObject

case class Timelines(timelines: List[TimelineBrief]) extends BaseObject

case class Token(on: String, pred: String, Variable: List[VarItem]) extends BaseObject

case class RequestedTickRange(min: Option[Int], max: Option[Int]) extends BaseObject

case class Timeline(name: String,
                    requested_tick_range: Option[RequestedTickRange],
                    tokens: List[Token]) extends BaseObject

//sealed abstract class VarItem extends BaseObject
//case class VarEnum( name: String, enum:     Map[String, Any]) extends VarItem
//case class VarBool( name: String, bool:     Map[String, Any]) extends VarItem
//case class VarFloat(name: String, float:    Map[String, Any]) extends VarItem
//case class VarInt(  name: String, int:      Map[String, Any]) extends VarItem
//case class VarDate( name: String, date:     Map[String, Any]) extends VarItem
//case class VarDur(  name: String, duration: Map[String, Any]) extends VarItem
//case object VarNull extends VarItem

case class VarItem(name: String, type_ : String, map: Map[String, String]) extends BaseObject

case class GoalEntry(id: String, href: String, on: String, pred: String, Variable: List[VarItem]) extends BaseObject

case class Goals(goals: List[GoalEntry]) extends BaseObject
