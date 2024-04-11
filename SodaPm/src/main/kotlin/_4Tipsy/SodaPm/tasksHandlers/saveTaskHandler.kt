

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson
import com.moandjiezana.toml.Toml


import java.io.File



// modules
import _4Tipsy.SodaPm.Main
import _4Tipsy.SodaPm.core.SubApp
import _4Tipsy.SodaPm.coreHelpers.InitsJsonEditor
import _4Tipsy.SodaPm.models.RegisteredAppModel





fun saveTaskHandler(): String {

  val newRegisteredApps = Main.NOT_REGISTERED_APPS
  InitsJsonEditor.registerUnregisteredApps(newRegisteredApps)

  Main.NOT_REGISTERED_APPS.clear()

  

  val resultJson = Gson().toJson(mapOf("result" to "Success"))
  return resultJson
}
