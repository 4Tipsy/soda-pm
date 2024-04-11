
package _4Tipsy.SodaPm.models





data class InitsJsonModel (
  var _comment: String,

  var registeredApps: List<RegisteredAppModel>,
)



data class RegisteredAppModel (
  var id: Int,
  var name: String,
  var initiatorPath: String,
)