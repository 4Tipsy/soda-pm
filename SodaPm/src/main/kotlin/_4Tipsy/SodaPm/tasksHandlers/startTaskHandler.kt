

package _4Tipsy.SodaPm.tasksHandlers



import com.google.gson.Gson


// modules
import _4Tipsy.SodaPm.Main




fun startTaskHandler(id: Int): String {


  var result: String

  if(Main.APPS.containsKey(id)) {


    Main.APPS.get(id)!!.start() // start app

    
    result = "Started"


  } else {
    result = "No app with such ID"
  }


  val resultJson = Gson().toJson(mapOf("result" to result))
  return resultJson

}
