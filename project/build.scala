import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object PlanningServiceBuild extends Build {
  val Organization      = "org.mbari"
  val Name              = "odsstrex"
  val Version           = "0.1.0-SNAPSHOT"
  val ScalaVersion      = "2.10.0"
  val ScalatraVersion   = "2.2.1"

  lazy val project = Project (
    "odsstrex",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      //resolvers += "spray repo" at "http://repo.spray.io",
      libraryDependencies ++= Seq(
        "org.scalatra"                  %% "scalatra"                    % ScalatraVersion,
        "org.scalatra"                  %% "scalatra-scalate"            % ScalatraVersion,
        "org.scalatra"                  %% "scalatra-specs2"             % ScalatraVersion % "test",
        "org.scalatra"                  %% "scalatra-json"               % ScalatraVersion,
        "org.json4s"                    %% "json4s-jackson"              % "3.2.4",
        "com.github.nscala-time"        %% "nscala-time"                 % "0.4.0",
        "ch.qos.logback"                %  "logback-classic"             % "1.0.12" % "runtime",
        "com.typesafe"                  %% "scalalogging-slf4j"          % "1.0.1",
      //"io.spray"                      %% "spray-client"                % "1.0-M7",
        "net.databinder.dispatch"       %% "dispatch-core"               % "0.10.0",
        "org.json4s"                    %% "json4s-native"               % "3.2.4",

        "org.eclipse.jetty"              % "jetty-webapp"                % "8.1.12.v20130726" % "container",
        "org.eclipse.jetty.orbit"        % "javax.servlet"               % "3.0.0.v201112011016" % "container;provided;test" artifacts Artifact("javax.servlet", "jar", "jar")
      ),
      scalacOptions ++= Seq("-feature"),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  ).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)

}
