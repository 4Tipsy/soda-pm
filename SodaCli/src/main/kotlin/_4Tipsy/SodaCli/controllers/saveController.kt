

package _4Tipsy.SodaCli.controllers


import com.google.gson.Gson
import com.google.gson.JsonObject


// modules
import _4Tipsy.SodaCli.Requester
import _4Tipsy.SodaCli.models.StatusResModel



fun saveController() {



  val rawJson = Requester.handleTask("save")
  val result = Gson().fromJson(rawJson, JsonObject::class.java).get("result").getAsString()

  println("${result}")

  




}