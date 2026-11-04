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
    ThisBuild / scalafixDependencies += "org.scalatest" %% "autofix" % "3.1.0.1",
    addCompilerPlugin(scalafixSemanticdb) // enable SemanticDB
  )

lazy val examples = knnProject("spark-knn-examples").
  dependsOn(core).
  settings(run / fork := true, coverageExcludedPackages := ".*examples.*", publish / skip := true).
  settings(Dependencies.examples)
