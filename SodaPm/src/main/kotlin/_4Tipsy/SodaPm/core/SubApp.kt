

package _4Tipsy.SodaPm.core



import com.moandjiezana.toml.Toml
import org.jutils.jprocesses.JProcesses

import java.util.concurrent.TimeUnit
import java.lang.ProcessBuilder
import java.lang.Process
import java.io.File
import java.nio.file.Files




// modules
import _4Tipsy.SodaPm.Logger
import _4Tipsy.SodaPm.coreHelpers.SodaCommandBuilder
import _4Tipsy.SodaPm.models.InitiatorModel
import _4Tipsy.SodaPm.utils.getAppLocation
import _4Tipsy.SodaPm.Main





class SubApp(val id: Int, val name: String, val initiatorPath: String) {

  var process: Process? = null
  val errorsOnStartup = mutableListOf<String>()
  var restartOnFail: Boolean = true

  var restarts: Int = 0
  var startStateChecker: Thread? = null
  var state: AppState = AppState.STARTING

  val outLogPath: String
  val errLogPath: String



  init {
    Logger.info("Created subApp(${id}, ${name}, ${initiatorPath})")

    outLogPath = getAppLocation() + File.separator + "logs" + File.separator + name + ".out.log"
    errLogPath = getAppLocation() + File.separator + "logs" + File.separator + name + ".err.log"

    // create logfiles if necessary
    if (!File(outLogPath).exists()) { File(outLogPath).createNewFile() }
    if (!File(errLogPath).exists()) { File(errLogPath).createNewFile() }
  }







  // ----------- //
  // - STARTER - //
  // ----------- //

  private fun _validateInitiatorAndTryToStart() {

    // get initiator
    val initiatorFile = File(initiatorPath)

    if (!initiatorFile.exists()) {
      errorsOnStartup.add("There is no such initiator file (${initiatorPath})!")

    } else {

      val toml = Toml().read(initiatorFile).getTable("Initiator") ?: Toml().read("im=crutch")
      val initiator = toml.to(InitiatorModel::class.java)

      // validate initiator

      // // check for missing fields
      val requiredFields = listOf("name", "cwd", "command", "restart_on_fail")
      for (field in initiator::class.java.fields) {
        if (field.name in requiredFields) {
          if (field.type == null) {
            errorsOnStartup.add("Field ${field.name} is required, but missing!")
          }
        }
      }

      // // check cwd
      if ( !File(initiator.cwd).exists() && !File(initiator.cwd).isDirectory() ) {
        errorsOnStartup.add("CWD '${initiator.cwd}' does not exist!")
      }


      // // check max ram restart var
      if (initiator.max_ram_restart < 0) {
        errorsOnStartup.add("Field 'max_ram_restart' must be a positive integer or 0!")
      }
      

      // if initiator valid
      if (errorsOnStartup.isEmpty()) {
        restartOnFail = initiator.restart_on_fail

        // build the process
        val validCommand = SodaCommandBuilder.create(initiator.command)

        val processBuilder = ProcessBuilder()

                  .command(validCommand)
                  .directory(File(initiator.cwd))

                  .redirectOutput( File(outLogPath) )
                  .redirectError( File(errLogPath) )
        

        // // env vars
        initiator.env_vars.forEach {
          (key, value) -> processBuilder.environment().put(key, value)
        }

        // // start
        process = processBuilder.start()
      }
                
    }
  }








  // ------------- //
  // -  checker  - //
  // ------------- //



  private fun startStateChecker() {

    Logger.info("APP ${id} -> starting, soon be working")

    state = AppState.STARTING
    while (restarts <= 10) {

      // start proccess if not running
      if (process == null) {
        _validateInitiatorAndTryToStart()

      // else assign state
      } else {
        if (process!!.isAlive()) {
          state = AppState.WORKING

        } else if (process!!.exitValue() == 0) {
          state = AppState.ENDED
          Logger.info("APP ${id} -> ended??")
          break

        } else if (state == AppState.STOPPED) {
          Logger.info("APP ${id} -> stopped")
          break
        

        } else {
          state = AppState.ERRORED
          if (restartOnFail && restarts < 10) { Logger.info("APP ${id} -> errored, restarting (${restarts}/10)") }
          else { Logger.info("APP ${id} -> errored (finally)") }
        }
      }


      TimeUnit.SECONDS.sleep(3) // give some time for the process to start


      // if initiator validation failed - break
      if (!errorsOnStartup.isEmpty()) {
        state = AppState.ERRORED_ON_STARTUP
        Logger.info("APP ${id} -> errored_on_startup")
        break
      }

      // break if SubApp was not supposed to restart on fail
      if (restartOnFail == false) { break }

      // if still errored
      if (process?.isAlive == false) {
        restarts += 1
      }

      
    }

  }



  // ------------- //
  // - interface - //
  // ------------- //


  fun start() {

    restarts = 0

    if (startStateChecker != null && startStateChecker?.isAlive()?:false) {
      startStateChecker!!.stop()
    }

    startStateChecker = Thread(Runnable {startStateChecker()}) .also { it.start() }

    
  }



  fun stop() {
    process?.destroy()
    startStateChecker = null
    state = AppState.STOPPED
  }


  fun getIsRegistered(): Boolean {
    return (Main.NOT_REGISTERED_APPS.indexOfFirst { it.id == id }) == -1
  }




  fun getRamUsageInBytes(): Long {
    val pid = process?.pid()
    if (pid != null) {
      // get RAM usage
      val processInfo = JProcesses.getProcess(pid.toInt())
      val ramUsed = processInfo.getPhysicalMemory()
      return ramUsed.toLong()

    } else {
      return 0
    }
  }



  fun getCpuUsageInPercents(): Float {
    val pid = process?.pid()
    if (pid != null) {
      // get CPU usage
      val processInfo = JProcesses.getProcess(pid.toInt())
      val cpuUsed = processInfo.getCpuUsage().toFloat()
      return cpuUsed

    } else {
      return 0f
    }
  }

  fun getDiskUsageInBytes(): Int {
    return 0
  }



}











public enum class AppState {
  STARTING,
  ERRORED_ON_STARTUP,
  WORKING,
  ERRORED,
  STOPPED,

  ENDED,
  KILL_SWITH,
}
