# ReactiveMongo Support for Play JSON

This is a JSON serialization pack for [ReactiveMongo](http://reactivemongo.org), based on the JSON library of Play! Framework.

## Usage

In your `project/Build.scala`:

```scala
// only for Play 2.4.x
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo-play-json" % "0.11.9")
```

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.reactivemongo/reactivemongo-play-json_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.reactivemongo/reactivemongo-play-json_2.11/)

## Build manually

ReactiveMongo for Play2 can be built from this source repository.

    sbt publish-local

To run the tests, use:

    sbt test

[Travis](https://travis-ci.org/ReactiveMongo/ReactiveMongo-Play-Json): ![Travis build status](https://travis-ci.org/ReactiveMongo/ReactiveMongo-Play-Json.png?branch=master)

> As for [Play Framework](http://playframework.com/) 2.4, a JDK 1.8+ is required to build this plugin.

### Learn More

- [Complete documentation and tutorials](http://reactivemongo.org/releases/0.11/documentation/tutorial/play2.html)
- [Search or create issues](https://github.com/ReactiveMongo/ReactiveMongo-Play-Json/issues)
- [Get help](https://groups.google.com/forum/?fromgroups#!forum/reactivemongo)
- [Contribute](https://github.com/ReactiveMongo/ReactiveMongo/blob/master/CONTRIBUTING.md#reactivemongo-developer--contributor-guidelines)
