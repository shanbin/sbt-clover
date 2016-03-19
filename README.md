# SBT Clover Plugin

SBT plugin to integrate with Clover for Java code coverage

## How to Use

In project/plugins.sbt

```
resolvers += Resolver.url("Plugins",
  url("http://dl.bintray.com/shanbin/sbt-plugins/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.shanbin" %% "sbt-clover" % "0.0.1")
```

A single task to run tests with instrumented sources and generate XML and HTML reports

```
sbt clover:clover
```

## Scope

All settings and tasks are under `clover` scope

## Settings

Setting            | Default Value
------------------ | -------------
cloverLicensePath  | baseDirectory / "clover.license"
cloverVersion      | 4.0.6

e.g.

```
import clover.CloverSbtPlugin

cloverLicensePath in CloverSbtPlugin.cloverConfig := "/etc/clover/license.txt"
```

## Tasks

- clover:setup
- clover:test
- clover:clover
