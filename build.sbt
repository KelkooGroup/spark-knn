import Common._

inThisBuild(
  Seq(
    organization := "com.kelkoogroup",
    homepage := Some(url("https://github.com/KelkooGroup/spark-knn")),
    licenses := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer("baitaand", "Andrea Baita", "andrea.baita@kelkoogroup.com", url("https://github.com/KelkooGroup"))
    )
  )
)

lazy val root = (project in file("."))
  .aggregate(core, examples)
  .settings(
    name := "spark-knn",
    publish / skip := true // Prevent publishing the aggregate project
  )

lazy val core = knnProject("spark-knn-core").
  settings(
    name := "spark-knn"
  ).
  settings(Dependencies.core).
  settings(
    // Spark on Java 17 needs these module options (same list as Spark's JavaModuleOptions)
    Test / fork := true,
    Test / javaOptions ++= Seq(
      "-XX:+IgnoreUnrecognizedVMOptions",
      "--add-opens=java.base/java.lang=ALL-UNNAMED",
      "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
      "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
      "--add-opens=java.base/java.io=ALL-UNNAMED",
      "--add-opens=java.base/java.net=ALL-UNNAMED",
      "--add-opens=java.base/java.nio=ALL-UNNAMED",
      "--add-opens=java.base/java.util=ALL-UNNAMED",
      "--add-opens=java.base/java.util.concurrent=ALL-UNNAMED",
      "--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED",
      "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
      "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
      "--add-opens=java.base/sun.security.action=ALL-UNNAMED",
      "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED"
    )
  ).
  settings(
    ThisBuild / scalafixDependencies += "org.scalatest" %% "autofix" % "3.1.0.1",
    addCompilerPlugin(scalafixSemanticdb) // enable SemanticDB
  )

lazy val examples = knnProject("spark-knn-examples").
  dependsOn(core).
  settings(run / fork := true, coverageExcludedPackages := ".*examples.*", publish / skip := true).
  settings(Dependencies.examples)
