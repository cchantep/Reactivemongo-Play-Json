// Travis CI
import Compiler.{ playLower, playUpper }
val travisEnv = taskKey[Unit]("Print Travis CI env")

travisEnv in Test := { // test:travisEnv from SBT CLI
  val specs = List[(String, List[String])](
    "PLAY_VERSION" -> List(playLower, playUpper)
  )

  lazy val integrationEnv = specs.flatMap {
    case (key, values) => values.map(key -> _)
  }.combinations(specs.size).toList

  @inline def integrationVars(flags: List[(String, String)]): String =
    flags.map { case (k, v) => s"$k=$v" }.mkString(" ")

  def integrationMatrix =
    integrationEnv.map(integrationVars).map { c => s"  - $c" }

  def matrix = (("env:" +: integrationMatrix :+
    "matrix: " :+ "  exclude: ") ++ (
    integrationEnv.flatMap { flags =>
      if (/* time-compat exclusions: */
        flags.contains("PLAY_VERSION" -> playUpper)) {
        List(
          "    - scala: 2.11.12",
          s"      env: ${integrationVars(flags)}"
        )
      } else if (/* time-compat exclusions: */
        flags.contains("PLAY_VERSION" -> playLower)) {
        List(
          s"    - scala: ${scalaVersion.value}",
          s"      env: ${integrationVars(flags)}"
        )
      } else List.empty[String]
    })
  ).mkString("\r\n")

  println(s"# Travis CI env\r\n$matrix")
}
