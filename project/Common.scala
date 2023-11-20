import com.typesafe.sbt.GitVersioning
import sbt._
import Keys._
import com.typesafe.sbt.GitPlugin.autoImport._

object Common {
  val commonSettings = Seq(
    ThisBuild / organization := "com.github.saurfang",
    javacOptions ++= Seq("-source", "11", "-target", "11"),
    scalacOptions ++= Seq("-deprecation", "-feature"),
    git.useGitDescribe := true,
    git.baseVersion := "0.0.1",
    test / parallelExecution := false,
    updateOptions := updateOptions.value.withCachedResolution(true)
  )

  def knnProject(path: String): Project =
    Project(path, file(path)).
      enablePlugins(GitVersioning).
      settings(name := path).
      settings(Dependencies.Versions).
      settings(commonSettings)
}
