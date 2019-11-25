import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

//import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys._

object Common extends AutoPlugin {
  //import com.typesafe.tools.mima.core._

  override def trigger = allRequirements
  override def requires = JvmPlugin

  val previousVersion = "0.12.1"

  private val silencerVer = "1.4.4"

  val playVersion = settingKey[String]("Play version")
  val playDir = settingKey[String]("Play source directory")
  import Compiler.{ playLower, playUpper }

  override def projectSettings = Compiler.settings ++ Seq(
    organization := "org.reactivemongo",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.typesafeRepo("releases")),
    playVersion := {
      sys.env.get("PLAY_VERSION").getOrElse {
        if (scalaBinaryVersion.value == "2.11") playLower
        else playUpper
      }
    },
    playDir := {
      if (playVersion.value startsWith "2.5") "play-upto2.5"
      else "play-2.6+"
    },
    unmanagedSourceDirectories in Compile += {
      (sourceDirectory in Compile).value / playDir.value
    },
    unmanagedSourceDirectories in Test += {
      (sourceDirectory in Test).value / playDir.value
    },
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "4.8.1",
      "org.slf4j" % "slf4j-simple" % "1.7.29").map(_ % Test),
    libraryDependencies ++= Seq(
      compilerPlugin(
        ("com.github.ghik" %% "silencer-plugin" % silencerVer).
          cross(CrossVersion.full)),
      ("com.github.ghik" %% "silencer-lib" % silencerVer % Provided).
        cross(CrossVersion.full)),
    // mimaDefaultSettings
    mimaFailOnNoPrevious := false,
    mimaPreviousArtifacts := {
      if (scalaBinaryVersion.value != "2.13") {
        Set(organization.value %% moduleName.value % previousVersion)
      } else {
        Set.empty[ModuleID]
      }
    }
  ) ++ Publish.settings
}
