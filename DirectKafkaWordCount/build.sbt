name := "SparkLogging"

version := "1.0"

organization := "org.apache"

scalaVersion := "2.11.8"

val sparkVersion = "2.0.0.2.5.0.0-1245"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided"
        excludeAll ExclusionRule(organization = "org.mortbay.jetty"),
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion
)

assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
