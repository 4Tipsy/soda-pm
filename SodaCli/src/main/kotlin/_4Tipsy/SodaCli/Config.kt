
package _4Tipsy.SodaCli


import com.moandjiezana.toml.Toml

import java.io.File

// modules
import _4Tipsy.SodaCli.models.MainConfigModel
import _4Tipsy.SodaCli.models.exceptions.InvalidConfigException
import _4Tipsy.SodaCli.models.exceptions.AppIntegrityException
import _4Tipsy.SodaCli.utils.getAppLocation





object Config {

  var Instance: MainConfigModel // config singleton


  init {
    val configFile = File(getAppLocation() + File.separator + "Config.toml")

    // if no config file
    if (!configFile.exists()) {
      throw AppIntegrityException("Config.toml was not found in app location")
    }

    // if invalid config file
    val configToml = Toml().read(configFile)

    if (!configToml.containsTable("Config")) {
      throw AppIntegrityException("Config.toml does not contains \"Config\" table")
    }

    
    

    // assign config instance
    Instance = configToml.getTable("Config").to(MainConfigModel::class.java) 
  }


}