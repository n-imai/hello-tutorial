lazy val `tutorial-play` = project.
  settings(TutorialSettings.commons).
  settings(libraryDependencies ++= Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  )).
  enablePlugins(PlayScala).
  dependsOn(`tutorial-domain`)

lazy val `tutorial-domain` = project.
  settings(TutorialSettings.commons).
  settings(libraryDependencies ++= Seq(
    "com.microsoft.sqlserver" % "mssql-jdbc" % "6.2.1.jre8",
    "org.scalikejdbc" %% "scalikejdbc" % "3.2.1",
    "org.scalikejdbc" %% "scalikejdbc-config" % "3.2.1",
  ))

lazy val root = Project("tutorial-root", file("."))
