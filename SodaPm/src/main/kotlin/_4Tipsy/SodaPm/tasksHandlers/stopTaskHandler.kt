

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson


// modules
import _4Tipsy.SodaPm.Main




fun stopTaskHandler(id: Int): String {


  var result: String

  if(Main.APPS.containsKey(id)) {

    Main.APPS.get(id)!!.stop() // stop app
    result = "Stopped"


  } else {
    result = "No app with such ID"
  }


  val resultJson = Gson().toJson(mapOf("result" to result))
  return resultJson

}
