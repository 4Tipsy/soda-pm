

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand


// modules
import _4Tipsy.SodaCli.controllers.statusController



class StatusCmd : CliktCommand(name="status", help="Prints info about all sub-apps") {



  override fun run() {
    statusController()
  }
}