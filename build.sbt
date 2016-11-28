name := "weekends"

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.4"

mainClass in assembly := Some("ps.csv.weekends")
