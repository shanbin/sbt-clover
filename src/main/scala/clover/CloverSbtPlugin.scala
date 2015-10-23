package clover

import sbt._

object Imports {
  object CloverKeys {
    lazy val cloverLicense = settingKey[Option[String]]("location of clover license file")
    lazy val cloverVersion = settingKey[String]("version of clover dependency")
    lazy val clover = taskKey[Unit]("instrument sources, run test, and generate reports")
  }
}

object CloverSbtPlugin extends AutoPlugin {

  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  val autoImport = Imports

  import autoImport._
  import CloverKeys._

  override lazy val projectSettings = Seq(
    cloverLicense := None,
    cloverVersion := "4.0.6",
    clover := runClover()
    //javaSource in Compile := null,
    //javaSource in Test := null,
    //target := null
  )

  def runClover(): Unit = {
    // instrument
    // test
    // generate XMl and HTML reports
    println("Starting Clover ...")
  }
  
}
