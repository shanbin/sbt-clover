name := "sbt-clover"

organization := "com.github.shanbin"

sbtPlugin := true

ScriptedPlugin.scriptedSettings

scriptedBufferLog := false

libraryDependencies += "com.atlassian.clover" % "clover" % "4.0.6"