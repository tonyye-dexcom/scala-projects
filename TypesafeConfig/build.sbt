name := "TypesafeConfig"

version := "1.0"

organization := "com.dexcom"

scalaVersion := "2.11.8"

val sparkVersion = "1.6.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "com.typesafe" % "config" % "1.3.1"
)
