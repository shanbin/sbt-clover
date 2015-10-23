name := "sbt-clover"

organization := "io.github.shanbin"

sbtPlugin := true

ScriptedPlugin.scriptedSettings

scriptedLaunchOpts ++= Seq(
  "-Dplugin.version=" + version.value
)