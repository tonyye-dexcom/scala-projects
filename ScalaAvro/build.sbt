name := "ScalaAvro"

version := "1.0"

organization := "com.dexcom"

scalaVersion := "2.11.8"

libraryDependencies := Seq(
  "org.apache.avro" % "avro" % "1.8.0",
  "org.scala-lang" %% "scala-reflect" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)
