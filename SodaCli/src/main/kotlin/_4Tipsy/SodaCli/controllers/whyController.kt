

package _4Tipsy.SodaCli.controllers


import com.google.gson.Gson
import com.google.gson.JsonObject


// modules
import _4Tipsy.SodaCli.Requester
import _4Tipsy.SodaCli.models.StatusResModel


fun whyController(ids: List<Int>) {


  for (id in ids) {

    val rawJson = Requester.handleTask("why", id)
    val result = Gson().fromJson(rawJson, JsonObject::class.java).get("result").getAsJsonArray()

    
    var idx = 1
    result.forEach {
      println("${idx}) ${it.getAsString()}")
      idx++
    }

  }




}