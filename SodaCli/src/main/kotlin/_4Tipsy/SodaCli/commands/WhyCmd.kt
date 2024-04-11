

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.arguments.multiple


// modules
import _4Tipsy.SodaCli.controllers.whyController






class WhyCmd : CliktCommand(name="why", help="Checks errors on startup of sub-up with <ID> (KILL_SWITCH state counts)") {

  val ids: List<Int> by argument("id").int().multiple()


  override fun run() {
    whyController(ids)
  }
}
