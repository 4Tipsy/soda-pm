

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson

import java.text.DecimalFormat



// modules
import _4Tipsy.SodaPm.Main
import _4Tipsy.SodaPm.models.dialoged.AppStatusModel



fun statusTaskHandler(): String {


  // gather apps status info into list
  val apps = mutableListOf<AppStatusModel>()
  for (app in Main.APPS.values) {

    var _ramUsageFormated = DecimalFormat("#.##").format(app.getRamUsageInBytes().toDouble() / 1024 / 1024)
    val ramUsageFormated = _ramUsageFormated.toString() + " MB"

    val _cpuUsageFormated = DecimalFormat("#.##").format(app.getCpuUsageInPercents())
    val cpuUsageFormated = _cpuUsageFormated.toString() + " %"




    apps.add(AppStatusModel(
      app.id,
      app.name,

      app.getIsRegistered(),

      app.state.toString(),
      app.restarts,

      ramUsageFormated,
      cpuUsageFormated,
    ))
  }


  val jsonString = Gson().toJson(mapOf("apps" to apps))
  return jsonString

}
