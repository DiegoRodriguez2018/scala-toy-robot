import sbt.util

name := "toyrobot"

version := "0.3.1"

scalaVersion := "2.13.1"

val specs2Version = "4.8.0"

libraryDependencies ++= Seq(
  "org.specs2"                  %% "specs2-core"                   % specs2Version            % Test,
)
