import bintray.Keys._
import sbt.ScriptedPlugin._

lazy val commonSettings = Seq(
  version in ThisBuild := "0.0.1",
  organization in ThisBuild := "com.github.shanbin",
  libraryDependencies += "com.atlassian.clover" % "clover" % "4.0.6"
) ++ scriptedSettings

lazy val root = (project in file(".")).
  settings(commonSettings ++ bintrayPublishSettings: _*).
  settings(
    sbtPlugin := true,
    name := "sbt-clover",
    description := "SBT plugin to integrate with Clover for Java code coverage",
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    publishMavenStyle := false,
    repository := "sbt-plugins",
    bintrayOrganization in bintray := None
  )
