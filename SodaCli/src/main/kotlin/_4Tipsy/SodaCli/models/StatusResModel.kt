


package _4Tipsy.SodaCli.models








class StatusResModel {

  var apps: List<AppStatusModel>? = null
}



class AppStatusModel {
  var id: Int? = null
  var name: String? = null

  var isRegistered: Boolean? = null

  var status: String? = null
  var restarts: Int? = null

  var ramUsed: String? = null
  var cpuUsed: String? = null
} 