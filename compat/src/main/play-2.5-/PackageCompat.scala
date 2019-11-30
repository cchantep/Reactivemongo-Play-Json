package reactivemongo.play.json.compat

import play.api.libs.json.JsBoolean

private[compat] trait PackageCompat {
  lazy val JsTrue = JsBoolean(true)

  lazy val JsFalse = JsBoolean(false)
}
