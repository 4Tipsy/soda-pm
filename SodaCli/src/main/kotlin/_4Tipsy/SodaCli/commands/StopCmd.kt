

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.arguments.multiple


// modules
import _4Tipsy.SodaCli.controllers.stopController






class StopCmd : CliktCommand(name="stop", help="Stops sub-up with <ID>") {

  val ids: List<Int> by argument("id").int().multiple()


  override fun run() {
    stopController(ids)
  }
}
