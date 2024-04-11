

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand


// modules
import _4Tipsy.SodaCli.controllers.saveController



class SaveCmd : CliktCommand(name="save", help="SAVES current sub-apps list (register unregistered ones)") {



  override fun run() {
    saveController()
  }
}