import com.github.sbt.git.GitPlugin.autoImport.*
import com.github.sbt.git.GitVersioning
import sbt.*
import sbt.Keys.*
import xerial.sbt.Sonatype.autoImport.*
import xerial.sbt.Sonatype.sonatypeCentralHost

object Common {
  val commonSettings = Seq(
    ThisBuild / organization := "com.kelkoogroup",
    ThisBuild / homepage := Some(url("https://github.com/KelkooGroup/spark-knn")),
    ThisBuild / sonatypeCredentialHost := sonatypeCentralHost,
    ThisBuild / scmInfo := Some(
      ScmInfo(
        url("https://github.com/KelkooGroup/spark-knn"),
        "scm:git:git@github.com:KelkooGroup/spark-knn.git"
      )
    ),
    ThisBuild / versionScheme := Some("semver-spec"),
    publishMavenStyle := true,
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
    publishTo := sonatypePublishToBundle.value,
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
