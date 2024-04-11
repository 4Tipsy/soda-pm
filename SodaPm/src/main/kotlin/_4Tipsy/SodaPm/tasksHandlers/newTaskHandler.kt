

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson
import com.moandjiezana.toml.Toml


import java.io.File



// modules
import _4Tipsy.SodaPm.Main
import _4Tipsy.SodaPm.core.SubApp
import _4Tipsy.SodaPm.coreHelpers.InitsJsonEditor
import _4Tipsy.SodaPm.models.RegisteredAppModel





fun newTaskHandler(initiatorPath: String): String {


  var result: String
  val initiatorFile = File(initiatorPath)



  if (initiatorFile.exists() && initiatorPath.endsWith(".initiator.toml")) {

    // get vars
    val newFreeId = InitsJsonEditor.getNewFreeId()
    val possibleName = Toml().read(initiatorFile).getTable("Initiator")?.getString("name") ?: "_?"


    // create instances
    val newSubApp = SubApp(newFreeId, possibleName, initiatorPath)
    val newNotRegisteredApp = RegisteredAppModel(newFreeId, possibleName, initiatorPath)


    // add to SodaPm
    Main.APPS[newFreeId] = newSubApp.also { it.start() } // start app
    Main.NOT_REGISTERED_APPS.add(newNotRegisteredApp)

    // result var
    result = "Success (id: ${newFreeId})"



  } else {
    result = "There is no such initiator file (${initiatorPath})!"
  }




  
  val resultJson = Gson().toJson(mapOf("result" to result))
  return resultJson
}
