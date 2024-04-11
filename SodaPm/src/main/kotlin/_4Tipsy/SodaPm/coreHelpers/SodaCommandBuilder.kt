

package _4Tipsy.SodaPm.coreHelpers



object SodaCommandBuilder {
  





  fun create(rawPrompt: String): List<String> {

    return listOf("/bin/sh", "-c", rawPrompt)
  }
}