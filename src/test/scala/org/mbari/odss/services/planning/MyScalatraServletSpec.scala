package org.mbari.odss.services.planning

import org.scalatra.test.specs2._
import org.mbari.odss.services.planning.trex.TrexClient

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class MyScalatraServletSpec extends MutableScalatraSpec {

  val trexClient = new TrexClient("http://threadfish.shore.mbari.org:2222/rest/")
  addServlet(new PlanningServlet(trexClient), "/*")

  "GET /tokens on PlanningServlet" should {
    "return status 200" in {
      get("/tokens") { status must_== 200 }
    }
  }

  "GET /goals on PlanningServlet" should {
    "return status 200" in {
      get("/goals") { status must_== 200 }
    }
  }

}
