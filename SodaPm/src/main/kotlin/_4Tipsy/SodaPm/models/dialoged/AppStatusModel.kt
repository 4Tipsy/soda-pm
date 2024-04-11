

package _4Tipsy.SodaPm.models.dialoged








data class AppStatusModel (
  var id: Int,
  var name: String,

  var isRegistered: Boolean,

  var status: String,
  var restarts: Int,

  var ramUsed: String,
  var cpuUsed: String,
)