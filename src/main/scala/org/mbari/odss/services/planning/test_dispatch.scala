package org.mbari.odss.services.planning


object test_dispatch extends App {

  import dispatch._, Defaults._
  val svc = url("http://localhost:8080/tokens")
  val country = Http(svc OK as.String)


  println("waiting response")
  for (c <- country) {
    println("here it is: " + c)
  }

  println("country() = " + country())
}
