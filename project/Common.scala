import sbt.Keys._
import sbt._
import scala.language.experimental.macros
import scala.reflect.macros.Context

object Common {
  val commonSettings = Seq(
    organization in ThisBuild := "com.github.saurfang",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq("-target:jvm-1.8", "-deprecation", "-feature"),
    //git.useGitDescribe := true,
    git.baseVersion := "0.3.1",
    parallelExecution in test := false,
    updateOptions := updateOptions.value.withCachedResolution(true),
//    sparkVersion := "3.1.2",
//    sparkComponents += "mllib",
//    spIgnoreProvided := true
  )

  def knnProject(path: String): Project = macro knnProjectMacroImpl

  def knnProjectMacroImpl(c: Context)(path: c.Expr[String]) = {
    import c.universe._
    reify {
      (Project.projectMacroImpl(c).splice in file(path.splice)).
        enablePlugins(GitVersioning).
        settings(name := path.splice).
        settings(Dependencies.Versions).
        settings(commonSettings)
    }
  }
}
