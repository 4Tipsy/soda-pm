

package _4Tipsy.SodaCli.models.exceptions


class AppIntegrityException : Exception {
  // will be thrown on invalid config or initiator-file
    
  constructor(message: String) : super(message) 
  constructor(message: String, cause: Throwable) : super(message, cause) 
}