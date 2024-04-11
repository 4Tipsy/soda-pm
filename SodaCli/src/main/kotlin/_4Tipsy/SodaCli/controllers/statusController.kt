

package _4Tipsy.SodaCli.controllers


import com.google.gson.Gson


// modules
import _4Tipsy.SodaCli.Requester
import _4Tipsy.SodaCli.models.StatusResModel
import _4Tipsy.SodaCli.views.viewStatusTable



fun statusController() {
  var isFirstTable = true


  while(true) {
    val rawJson = Requester.handleTask("status")
    
    val apps = Gson().fromJson(rawJson, StatusResModel::class.java)
    val toPrint = viewStatusTable(apps.apps!!)

    val upTo = toPrint.count {it == '\n'}
    val tableWidth = toPrint.split("\n")[0].length



    if (!isFirstTable) {
      println("\u001B[${upTo+2}A")
      println( (" ".repeat(tableWidth+1)+"\n" ).repeat(upTo) )
      println("\u001B[${upTo+2}A")

    } else {
      isFirstTable = false
    }

   
    
    println(toPrint)

    Thread.sleep(3000) // 3 secs
  }

  
}