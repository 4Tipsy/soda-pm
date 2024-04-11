
package _4Tipsy.SodaPm



import java.io.File


// modules
import _4Tipsy.SodaPm.Logger
import _4Tipsy.SodaPm.core.SubApp
import _4Tipsy.SodaPm.core.handleHttp
import _4Tipsy.SodaPm.coreHelpers.InitsJsonEditor
import _4Tipsy.SodaPm.models.RegisteredAppModel
import _4Tipsy.SodaPm.models.exceptions.AppIntegrityException
import _4Tipsy.SodaPm.utils.getAppLocation





object Main {

  var NOT_REGISTERED_APPS = mutableListOf<RegisteredAppModel>()
  var APPS = mutableMapOf<Int, SubApp>()
}



fun main() {

  Logger.info("SodaPm started...")


  // quick check
  val templateInitiator = getAppLocation() + File.separator + "template.initiator.toml"
  if ( !File(templateInitiator).exists() ) {
    throw AppIntegrityException("File \"template.initiator.toml\" does not exist at app location!")
  }

  

  // invoke registered apps
  val registeredApps = InitsJsonEditor.getAllRegisteredApps()
  if (registeredApps.size > 0) {
    for (registeredApp in registeredApps) {
      val subApp = SubApp(registeredApp.id, registeredApp.name, registeredApp.initiatorPath)
      Main.APPS[registeredApp.id] = subApp

      // start subApp!!!
      subApp.start()

    }
  }




  // start server
  handleHttp()
}