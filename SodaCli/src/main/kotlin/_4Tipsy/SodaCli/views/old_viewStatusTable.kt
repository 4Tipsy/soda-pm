/*

package _4Tipsy.SodaCli.views


import com.github.freva.asciitable.AsciiTable
import com.github.freva.asciitable.Column
import com.github.freva.asciitable.OverflowBehaviour
import com.github.ajalt.mordant.rendering.TextColors.brightBlue
import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.rendering.TextColors.green



// modules
import _4Tipsy.SodaCli.models.AppStatusModel






fun _viewStatusTable(apps: List<AppStatusModel>): String {



  var table = AsciiTable.getTable(
    AsciiTable.FANCY_ASCII,
    apps,
    listOf(
      Column().header("ID").with({ app -> brightBlue(app.id.toString()) }),
      Column().header("NAME").maxWidth(16, OverflowBehaviour.ELLIPSIS_RIGHT).with({ app -> app.name }),
      Column().header("STATUS").maxWidth(9).with(
        // sry for this...
        { app -> if (app.status=="ERRORED"||app.status=="ERRORED_ON_STURTUP") red(app.status.toString()) else if (app.status=="WORKING") green(app.status.toString()) else app.status }
      ),
      Column().header("↺").with({ app -> app.restarts.toString() }),
      Column().header("RAM USED").with({ app -> app.ramUsed }),
      Column().header("CPU USED").with({ app -> app.cpuUsed }),
      Column().header("r").with({app -> if (app.isRegistered!!) "+" else "-" })
    )
  )

  table += brightBlue("\n\n# \"r\" = registered, not-registered apps will disapear after machine restart")
  table += brightBlue("\n# table autoupdates every 3 seconds, Ctrl+C to exit")


  return table

}*/