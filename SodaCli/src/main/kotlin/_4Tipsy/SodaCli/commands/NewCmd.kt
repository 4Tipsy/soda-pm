

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument


// modules
import _4Tipsy.SodaCli.controllers.newController




class NewCmd : CliktCommand(name="new", help="Tries to add sub-app related to <INITIATOR_PATH>") {

  val initiatorPath: String by argument("initiatorPath")


  override fun run() {
    newController(initiatorPath)
  }
}
