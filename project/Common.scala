import sbt._
import Keys._

object Common {
  val commonSettings = Seq(
    javacOptions ++= Seq("-source", "11", "-target", "11"),
    scalacOptions ++= Seq("-deprecation", "-feature"),
    test / parallelExecution := false,
    updateOptions := updateOptions.value.withCachedResolution(true)
  )

  def knnProject(path: String): Project =
    Project(path, file(path)).
      settings(name := path).
      settings(Dependencies.Versions).
      settings(commonSettings)
}
