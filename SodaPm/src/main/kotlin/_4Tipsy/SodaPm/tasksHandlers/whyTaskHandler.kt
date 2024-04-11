

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson


// modules
import _4Tipsy.SodaPm.Main




fun whyTaskHandler(id: Int): String {


  var result: List<String>

  if(Main.APPS.containsKey(id)) {

    val errorsOnStartUpList = Main.APPS.get(id)!!.errorsOnStartup // stop app
    result = errorsOnStartUpList


  } else {
    result = listOf("No app with such ID, LOL xd")
  }


  val resultJson = Gson().toJson(mapOf("result" to result))
  return resultJson

}
