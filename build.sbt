val scalatest  = "3.0.1"
val cats       = "0.9.0"

crossScalaVersions in ThisBuild := Seq("2.12.2", "2.11.11")
scalaVersion       in ThisBuild := crossScalaVersions.value.head

lazy val root = project.in(file("."))
  .aggregate(
    `empty-listJS`,
    `empty-listJVM`,
    `empty-list-catsJS`,
    `empty-list-catsJVM`,
    `testsJS`,
    `testsJVM`)
  .settings(noPublishSettings: _*)

lazy val `empty-listJS`  = `empty-list`.js
lazy val `empty-listJVM` = `empty-list`.jvm
lazy val `empty-list`    = crossProject
  .crossType(CrossType.Pure)
  .settings(publishSettings: _*)

lazy val `empty-list-catsJS`  = `empty-list-cats`.js
lazy val `empty-list-catsJVM` = `empty-list-cats`.jvm
lazy val `empty-list-cats`    = crossProject
  .crossType(CrossType.Pure)
  .settings(publishSettings: _*)
  .dependsOn(`empty-list`)
  .settings(libraryDependencies += "org.typelevel" %%% "cats" % cats)

lazy val `testsJS`  = `tests`.js
lazy val `testsJVM` = `tests`.jvm
lazy val `tests`    = crossProject
  .crossType(CrossType.Pure)
  .settings(noPublishSettings: _*)
  .dependsOn(`empty-list`, `empty-list-cats`)
  .settings(libraryDependencies += "org.scalatest" %%% "scalatest" % scalatest % "test")

scalacOptions in ThisBuild := Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard")

organization in ThisBuild := "in.nvilla"

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := Function.const(false),
  licenses := Seq(("MIT", url("http://opensource.org/licenses/MIT"))),
  homepage := Some(url("https://github.com/OlivierBlanvillain/empty-list")),
  publishMavenStyle := true,
  publishTo := {
    if (isSnapshot.value) Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    else                  Some("releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  },
  pomExtra := {
    <scm>
      <url>git@github.com:OlivierBlanvillain/empty-list.git</url>
      <connection>scm:git:git@github.com:OlivierBlanvillain/empty-list.git</connection>
    </scm>
    <developers>
      <developer>
        <id>OlivierBlanvillain</id>
        <name>Olivier Blanvillain</name>
        <url>https://github.com/OlivierBlanvillain/</url>
      </developer>
    </developers>
  }
)

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)
