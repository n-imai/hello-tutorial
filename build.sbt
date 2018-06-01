lazy val `tutorial-play` = project.
  settings(TutorialSettings.commons).
  settings(libraryDependencies ++= Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  )).
  enablePlugins(PlayScala).
  dependsOn(`tutorial-domain`)

lazy val `tutorial-domain` = project.
  settings(TutorialSettings.commons)

lazy val root = Project("tutorial-root", file("."))
