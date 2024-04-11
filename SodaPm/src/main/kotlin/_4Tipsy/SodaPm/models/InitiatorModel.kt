
package _4Tipsy.SodaPm.models


class InitiatorModel (

  var name: String,
  var cwd: String,

  var command: String,

  var env_vars: Map<String, String>,

  var restart_on_fail: Boolean,
  var max_ram_restart: Int,
)