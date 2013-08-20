package org.mbari.odss.services

import org.joda.time.format.DateTimeFormatter
import com.github.nscala_time.time.Imports._
import java.util.UUID
import scala.language.implicitConversions

/**
 * Some common utilities and basic definitions.
 */
package object planning {
  val fmt: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")

  implicit def s2d(str: String): DateTime = fmt.parseDateTime(str)

  val minDate: DateTime = "0000-01-01 00:00"
  val maxDate: DateTime = "9999-12-31 23:59"


  object TokenStatus extends Enumeration {
    type TokenStatus = Value

    val New       = Value("status_new")
    val NewSaved  = Value("status_new_saved")
    val Pending   = Value("status_pending")
    val Accepted  = Value("status_accepted")
  }

  implicit def ts2s(ts: TokenStatus.Value): String = ts.toString

  def newTid = UUID.randomUUID().toString


  implicit def s2b(s: String): Boolean = s == "true" || s == "1"

}
