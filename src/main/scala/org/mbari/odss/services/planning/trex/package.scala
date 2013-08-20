package org.mbari.odss.services.planning

package object trex {

  def prettyFormat(x: BaseObject, level: Int = 0): String = {
    val ind = "    " * level
    ind + (x match {

      case Help(help) =>
        "HELP:\n" + (help map (e => prettyFormat(e, level + 1))).mkString("\n")

      case HelpItem(href, info) =>
        "HelpItem: href=" +href+ " info=\n" +ind+ind+ info.replaceAll("\n", "\n"+ind+ind)

      case Tick(value, date) =>
        x.toString

      case TickRate(nanoseconds, duration) =>
        x.toString

      case x: TimelineBrief =>
        x.toString

      case Timelines(timelines) =>
        "Timelines:\n" + ((timelines map (e => prettyFormat(e, level + 1))).mkString("\n"))

      case Timeline(name, requested_tick_range, tokens) =>
        "name: " + name +
        "; requested_tick_range: " + (requested_tick_range map (e => prettyFormat(e, level))) + "\n" +
        ind+ind+ ((tokens map (e => prettyFormat(e, level + 1))).mkString("\n")).replaceAll("\n", "\n"+ind+ind)

      case Token(on, pred, variable) =>
        "on=" +on+ " pred=" +pred+ "\n" +
        (variable map (e => prettyFormat(e, level + 1))).mkString("\n")

      case RequestedTickRange(min, max) =>
        "RequestedTickRange: min=" +min + " max=" + max

      case x: VarItem =>
        x.toString

      case GoalEntry(id, href, on, pred, variable) =>
        "GoalEntry: id=" +id+ " href=" +href+ " on=" +on+ " pred=" + pred+ "\n" +
        (variable map (e => prettyFormat(e, level + 1))).mkString("\n")

      case Goals(goals) =>
        "Goals:\n" + (goals map (e => prettyFormat(e, level + 1))).mkString("\n")
    })
  }
}
