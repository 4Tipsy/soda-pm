

package _4Tipsy.SodaCli.commands


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.arguments.multiple


// modules
import _4Tipsy.SodaCli.controllers.delController






class DelCmd : CliktCommand(name="del", help="DELETES sub-up with <ID>") {

  val ids: List<Int> by argument("id").int().multiple()


  override fun run() {
    delController(ids)
  }
}
