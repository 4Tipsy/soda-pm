

package _4Tipsy.SodaCli


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.eagerOption
import com.github.ajalt.clikt.core.PrintMessage

import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.clikt.core.context
import com.github.ajalt.mordant.rendering.Theme
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.brightBlue


// modules
import _4Tipsy.SodaCli.utils.isSodaAlive
import _4Tipsy.SodaCli.utils.Theming
import _4Tipsy.SodaCli.commands.StatusCmd
import _4Tipsy.SodaCli.commands.StopCmd
import _4Tipsy.SodaCli.commands.StartCmd
import _4Tipsy.SodaCli.commands.NewCmd
import _4Tipsy.SodaCli.commands.SaveCmd
import _4Tipsy.SodaCli.commands.DelCmd
import _4Tipsy.SodaCli.commands.InitCmd
import _4Tipsy.SodaCli.commands.WhyCmd
import _4Tipsy.SodaCli.commands.ManualCmd





class SodaCli(t: Terminal) : CliktCommand(name="soda", printHelpOnEmptyArgs=true) {

  init {
    
    // theming
    context {
      terminal = t
      helpFormatter = { Theming(it) }
    }

    // --version
    eagerOption( listOf<String>("-v", "--version") ) {
      throw PrintMessage("SodaPm@4Tipsy "+brightBlue("v0.1.0"))
    }
  }

  override fun run() = Unit
}



fun main(args: Array<String>) {

  val theme = Theme {
    // Use ANSI-16 codes for help colors
    styles["info"] = TextColors.green
    styles["warning"] = TextColors.blue
    styles["danger"] = TextColors.magenta
    styles["muted"] = TextColors.gray

    // Remove the border around code blocks
    flags["markdown.code.block.border"] = false
  }



  // quick check
  if (
    !isSodaAlive() && 
    !( args.contains("-h") || args.contains("--help") || args.contains("-v") || args.contains("--version") ) // exec anyway if those keys
  ) {

    println("Seems like SodaPm is not started...")

  } else {

    SodaCli(Terminal(theme = theme))
    .subcommands(StatusCmd(), StopCmd(), StartCmd(), NewCmd(), SaveCmd(), DelCmd(), InitCmd(), WhyCmd(), ManualCmd())
    .main(args)
  }
}
