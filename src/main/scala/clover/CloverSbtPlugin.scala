package clover

import _root_.com.atlassian.clover.CloverInstr
import _root_.com.atlassian.clover.reporters.html.HtmlReporter
import _root_.com.atlassian.clover.reporters.xml.XMLReporter
import sbt.Keys._
import sbt._

object Imports {
  object CloverKeys {
    lazy val setup = taskKey[Unit]("instrument sources with clover")
    lazy val test = taskKey[Unit]("alias for test:test, overrides javaSource and fork")
    lazy val clover = taskKey[Unit]("generate clover XML and HTML reports, depends on setup and test")
    lazy val cloverLicensePath = settingKey[String]("clover license location")
    lazy val cloverVersion = settingKey[String]("clover version number, e.g. 4.0.6")
  }
}

object CloverSbtPlugin extends AutoPlugin {

  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  val autoImport = Imports.CloverKeys

  import autoImport._

  val cloverConfig = config("clover")

  val commonSettings = Seq(
    libraryDependencies += "com.atlassian.clover" % "clover" % (cloverVersion in cloverConfig).value
  )

  override lazy val projectSettings = inConfig(cloverConfig)(
    Seq(
      cloverLicensePath := (baseDirectory.value / "clover.license").toString,
      cloverVersion := "4.0.6",
      setup <<= setupDef,
      test <<= (test in Test) dependsOn setup,
      clover <<= reportDef dependsOn test,
      javaSource in Compile <<= cloverSource,
      javaSource in Test <<= cloverSourceT,
      fork in Test := true
    )
  ) ++ commonSettings

  def setupDef = Def.task[Unit] {
    System.setProperty("clover.license.path", cloverLicensePath.value)

    streams.value.log.debug("clover.license.path = " + System.getProperty("clover.license.path"))

    val source = (sourceDirectory in Compile).value / "java"
    val sourceT = (sourceDirectory in Test).value / "java"
    val cliArgs1 = Array("-i", cloverDb.value.toString, "-d", cloverSource.value.toString, "-s", source.toString)
    val cliArgs2 = Array("-i", cloverDb.value.toString, "-d", cloverSourceT.value.toString, "-s", sourceT.toString)

    // instrument main and test sources
    CloverInstr.mainImpl(cliArgs1)
    CloverInstr.mainImpl(cliArgs2)
  }

  def reportDef = Def.task {
    streams.value.log.info("Generating Clover XML and HTML reports")

    val cloverReports = cloverDir.value / "reports"
    val xmlArgs = Array("-i", cloverDb.value.toString, "-o", (cloverReports / "clover.xml").toString)
    val htmlArgs = Array("-i", cloverDb.value.toString, "-o", (cloverReports / "html").toString)

    XMLReporter.runReport(xmlArgs)
    HtmlReporter.runReport(htmlArgs)

    () // return Unit
  }

  def cloverDir = Def.setting[File] {
    crossTarget.value / "clover"
  }

  def cloverDb = Def.setting[File] {
    cloverDir.value / "clover.db"
  }

  def cloverSource = Def.setting[File] {
    cloverDir.value / "src"
  }

  def cloverSourceT = Def.setting[File] {
    cloverDir.value / "test"
  }
}
