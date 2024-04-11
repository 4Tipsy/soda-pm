

package _4Tipsy.SodaCli.commands.manualSubcommands


import com.github.ajalt.clikt.core.CliktCommand




class AppStatesCmd : CliktCommand(name="app-states") {



  override fun run() {
    echo("STARTING -> app is only starting...")
  }
}