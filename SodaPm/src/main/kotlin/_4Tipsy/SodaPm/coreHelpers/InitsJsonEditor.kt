
package _4Tipsy.SodaPm.coreHelpers



import java.io.File

import com.google.gson.Gson
import com.google.gson.GsonBuilder

// modules
import _4Tipsy.SodaPm.models.InitsJsonModel
import _4Tipsy.SodaPm.models.RegisteredAppModel
import _4Tipsy.SodaPm.models.exceptions.AppIntegrityException
import _4Tipsy.SodaPm.utils.getAppLocation






object InitsJsonEditor {


  fun getAllRegisteredApps(): List<RegisteredAppModel> {
    val initsJson = getInitsJson()
    return initsJson.registeredApps
  }


  fun getNewFreeId(): Int {
    val initsJson = getInitsJson()
    var id = 1 // lets count from 1

    while (initsJson.registeredApps.any { it.id == id }) {
      id += 1
    }

    return id
  }


  fun registerUnregisteredApps(newRegisteredApps: List<RegisteredAppModel>) {
    val initsJson = getInitsJson()
    val registeredApps_new = initsJson.registeredApps.plus(newRegisteredApps)

    initsJson.registeredApps = registeredApps_new
    saveInitsJson(initsJson)
  }


  fun delAppById(id: Int) {
    val initsJson = getInitsJson()
    initsJson.registeredApps = initsJson.registeredApps.filterNot { it.id == id }
    saveInitsJson(initsJson)
  }








  private fun getInitsJson(): InitsJsonModel {

    val initsJsonFile = File(getAppLocation() + "/inits.json")

    val initsJson = Gson().fromJson(initsJsonFile.readText(), InitsJsonModel::class.java)
    return initsJson
  }

  private fun saveInitsJson(initsJsonModel: InitsJsonModel) {
    val initsJson = GsonBuilder().setPrettyPrinting().create().toJson(initsJsonModel)
    val initsJsonFile = File(getAppLocation() + "/inits.json")
    initsJsonFile.writeText(initsJson)
  }


}