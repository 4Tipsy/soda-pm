

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.core.PrintMessage

import java.io.File
import java.nio.file.Files



// modules
import _4Tipsy.SodaCli.utils.getAppLocation
import _4Tipsy.SodaCli.models.exceptions.AppIntegrityException




class InitCmd : CliktCommand(name="init", help="Creates new <NAME>.initiator.toml file") {

  val _name: String? by option("-n", "--name", help="Name of the .initiator.toml")
  

  override fun run() {
    var name: String
    
    // if no --name
    if (_name == null) {
      print("Insert <NAME> for .initiator.toml: ")
      name = readLine() ?: ""
      println("")

    } else { name = _name!! }


    // validate name
    if (name == "") {
      throw PrintMessage("Name should not be empty :<")
    }
    if ( (name+".in..").startsWith("..")) {
      throw PrintMessage("Please, i don't allow names, which starts with \"..\" :<")
    }



    // action!!!
    val targetFile = File(name+".initiator.toml")

    if (targetFile.exists()) {
      throw PrintMessage("File ${name}.initiator.toml already exists :<")
    }




    val templateInitiatorTomlFile = File(getAppLocation() + File.separator + "template.initiator.toml")
    if (!templateInitiatorTomlFile.exists()) {
      throw AppIntegrityException("File \"template.initiator.toml\" does not exist at app location (at ${getAppLocation()})!")
    }
 


    Files.copy(
      templateInitiatorTomlFile.toPath(),
      targetFile.toPath()
    )


    echo("File \"${targetFile.getAbsolutePath()}\" created ><")

  }
}
