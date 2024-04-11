

package _4Tipsy.SodaCli.views



import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.Terminal




// modules
import _4Tipsy.SodaCli.models.AppStatusModel




fun viewStatusTable(apps: List<AppStatusModel>): String {



  val _table = table {
    header {
      row {
        cell(brightBlue("ID"))
        cell(brightBlue("NAME"))
        cell(brightBlue("STATUS"))
        cell(brightBlue("↺"))
        cell(brightBlue("RAM USED"))
        cell(brightBlue("CPU USED"))
        cell(brightBlue("r"))
      }
    }
    body {
      for (app in apps) {

        row {
          cell(brightBlue(app.id!!.toString()))
          cell(app.name)
          cell(app.status)
          cell(app.restarts)
          cell(app.ramUsed)
          cell(app.cpuUsed)
          cell({if (app.isRegistered!!) green("+") else red("-")}.invoke())
        }
      }
    }
  }


  var tableString = Terminal().render(_table)
  tableString += brightBlue("\n\n# \"r\" = registered, not-registered apps will disapear after machine restart")
  tableString += brightBlue("\n# table autoupdates every 3 seconds, Ctrl+C to exit")


  return tableString
}