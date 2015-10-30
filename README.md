# SBT Clover Plugin

SBT plugin to integrate with Clover for Java code coverage

## How to Use

In project/plugins.sbt

`addSbtPlugin("com.github.shanbin" %% "sbt-clover" % "0.0.1")`

A single task to run tests with instrumented sources and generate XML and HTMl reports

`sbt clover:clover`

## Scope

All settings and tasks are under `clover` scope

## Settings

Setting              | Default Value
-------------------- | -------------
`cloverLicensePath`  | `baseDirectory / "clover.license"`
`cloverVersion`      | `4.0.6`

## Tasks

`clover:setup`

`clover:test`

`clover:clover`


