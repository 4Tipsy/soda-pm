

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson


// modules
import _4Tipsy.SodaPm.Main
import _4Tipsy.SodaPm.coreHelpers.InitsJsonEditor




fun delTaskHandler(id: Int): String {


  var result: String

  if(Main.APPS.containsKey(id)) {

    var isRegistered = Main.APPS.get(id)!!.getIsRegistered()

    // remove app from SodaPm
    Main.APPS.get(id)!!.stop() // stop app
    Main.APPS.remove(id)

    // unregister app
    if (isRegistered) {
      InitsJsonEditor.delAppById(id)
    }
    if (!isRegistered) {
      Main.NOT_REGISTERED_APPS.removeAt(Main.NOT_REGISTERED_APPS.indexOfFirst { it.id == id })
    }


    result = "Deleted (id: ${id})"

    
  } else {
    result = "No app with such ID"
  }


  val resultJson = Gson().toJson(mapOf("result" to result))
  return resultJson

}
