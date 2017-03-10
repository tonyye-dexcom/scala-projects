name := "test_cassandra_connector"

scalaVersion := "2.11.8"

lazy val sparkVersion = "2.0.1"

libraryDependencies ++= Seq(
    //"org.apache.spark" %% "spark-core" % sparkVersion % "provided" excludeAll ExclusionRule(organization = "org.mortbay.jetty"),
    "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
    "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0-RC1"
    )

version := "1.0.0-SNAPSHOT"

// set the main class for packaging the main jar
mainClass in (Compile, packageBin) := Some("TestCassandraConnector")
