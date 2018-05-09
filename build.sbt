name := "kafka-step1"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq("org.apache.kafka" %% "kafka" % "1.1.0",
  "com.typesafe" % "config" % "1.3.3",
  "org.springframework.boot" % "spring-boot-starter" % "2.0.1.RELEASE",
  "org.springframework.kafka" % "spring-kafka" % "2.1.6.RELEASE",
  "org.projectlombok" % "lombok" % "1.16.16")