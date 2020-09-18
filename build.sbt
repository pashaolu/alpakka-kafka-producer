name := "alpakka-kafka-producer"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.4"
  ,"com.github.javafaker" % "javafaker" % "1.0.2"
  , "io.spray" %% "spray-json" % "1.3.5"
)
