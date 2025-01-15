import com.github.sbt.git.GitVersioning
import sbt._
import Keys._
import com.github.sbt.git.GitPlugin.autoImport._
import xerial.sbt.Sonatype.autoImport._
import xerial.sbt.Sonatype.sonatypeCentralHost

object Common {
  val commonSettings = Seq(
    ThisBuild / organization := "com.kelkoogroup",
    ThisBuild / homepage := Some(url("https://github.com/KelkooGroup/spark-knn")),
    ThisBuild / sonatypeCredentialHost := sonatypeCentralHost,
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
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
