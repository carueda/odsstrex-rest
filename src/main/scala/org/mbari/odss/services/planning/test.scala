package org.mbari.odss.services.planning

import scala.collection.mutable


object test extends App {

  object TokenStatus extends Enumeration {
    type TokenStatus = Value

    val New       = Value("status_new")
    val Pending   = Value("status_pending")
    val Accepted  = Value("status_accepted")
  }
  import TokenStatus._

  def isNotNew(d: TokenStatus) = d != New

  TokenStatus.values filter isNotNew foreach println
  /* =>
    status_pending
    status_accepted
   */

  val m = mutable.ArrayBuffer(1,2,3,4,5)
  m filter { _ > 2 } foreach { m -= _ }

  println("m = " + m)
}
