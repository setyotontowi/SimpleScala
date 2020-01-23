import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"
ThisBuild / mainClass        := Some("example.Hello")

lazy val root = (project in file("."))
  .settings(
    name := "Test1",
    libraryDependencies += scalaTest % Test
  )

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.24"

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
