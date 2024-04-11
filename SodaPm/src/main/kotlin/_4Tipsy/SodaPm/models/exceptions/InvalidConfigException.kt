

package _4Tipsy.SodaPm.models.exceptions


class InvalidConfigException : Exception {
  // will be thrown on invalid config or initiator-file
    
  constructor(message: String) : super(message) 
  constructor(message: String, cause: Throwable) : super(message, cause) 
}