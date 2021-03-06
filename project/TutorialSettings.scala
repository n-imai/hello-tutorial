import sbt._
import Keys._
import sbt.Def.SettingList

object TutorialSettings {
  lazy val commons = new SettingList(Seq(
    scalaVersion := "2.12.5",
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xfuture",
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.4" % Test,
    ),
  ))
}
