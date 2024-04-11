

package _4Tipsy.SodaPm.core



import com.google.gson.Gson
import com.google.gson.JsonObject

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.io.OutputStream
import java.net.InetSocketAddress





// modules
import _4Tipsy.SodaPm.Logger
import _4Tipsy.SodaPm.Config
import _4Tipsy.SodaPm.tasksHandlers.statusTaskHandler
import _4Tipsy.SodaPm.tasksHandlers.stopTaskHandler
import _4Tipsy.SodaPm.tasksHandlers.startTaskHandler
import _4Tipsy.SodaPm.tasksHandlers.newTaskHandler
import _4Tipsy.SodaPm.tasksHandlers.saveTaskHandler
import _4Tipsy.SodaPm.tasksHandlers.delTaskHandler
import _4Tipsy.SodaPm.tasksHandlers.whyTaskHandler




fun handleHttp() {

  val port = Config.Instance.port
  val server = HttpServer.create(InetSocketAddress(port), 0)
  server.createContext("/soda-task", PostHandler_sodaTask())
  server.createContext("/", GetHandler())

  Logger.info("SodaPm listening on port ${port}")
  server.start()
}




class PostHandler_sodaTask : HttpHandler {

    

    override fun handle(exchange: HttpExchange) {

        val gson = Gson()

        if ("POST" != exchange.requestMethod) {
            sendResponse(exchange, 405, "Method Not Allowed")
            return
        }

        val requestBody = exchange.requestBody.bufferedReader().readText()
        var responseBody: String

        // handling request
        val requestBodyJson = gson.fromJson(requestBody, JsonObject::class.java)
        if (requestBodyJson.has("task")) {



          // HANDLERS
          when (requestBodyJson.get("task").getAsString()) {


            "status" -> {
              Logger.info("POST /soda-task -> STATUS")
              responseBody = statusTaskHandler()
            }


            "stop" -> {
              val id = requestBodyJson.get("id").getAsInt()
              Logger.info("POST /soda-task -> STOP ${id}")
              responseBody = stopTaskHandler(id)
            }


            "start" -> {
              val id = requestBodyJson.get("id").getAsInt()
              Logger.info("POST /soda-task -> START ${id}")
              responseBody = startTaskHandler(id)
            }


            "new" -> {
              val initiatorPath = requestBodyJson.get("initiatorPath").getAsString()
              Logger.info("POST /soda-task -> NEW ${initiatorPath}")
              responseBody = newTaskHandler(initiatorPath)
            }


            "save" -> {
              Logger.info("POST /soda-task -> SAVE")
              responseBody = saveTaskHandler()
            }


            "del" -> {
              val id = requestBodyJson.get("id").getAsInt()
              Logger.info("POST /soda-task -> DEL ${id}")
              responseBody = delTaskHandler(id)
            }


            "why" -> {
              val id = requestBodyJson.get("id").getAsInt()
              Logger.info("POST /soda-task -> WHY ${id}")
              responseBody = whyTaskHandler(id)
            }


            else -> {
              responseBody = gson.toJson(mapOf("error" to "task not found"))
            }
          }




        // if invalid json
        } else {
          responseBody = gson.toJson(mapOf("error" to "task not found"))
        }


        sendResponse(exchange, 200, responseBody)
    }






  private fun sendResponse(exchange: HttpExchange, statusCode: Int, message: String) {
    try {
      val response = message.toByteArray()
      exchange.responseHeaders.add("Content-Type", "application/json")
      exchange.sendResponseHeaders(statusCode, response.size.toLong())
      val os: OutputStream = exchange.responseBody
      os.write(response)
      os.close()
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }
}






class GetHandler : HttpHandler {



  override fun handle(exchange: HttpExchange) {

    Logger.info("GET /")
    sendResponse(exchange, 200, "Hello from SodaPm >///<")
  }



  private fun sendResponse(exchange: HttpExchange, statusCode: Int, message: String) {
    try {
      val response = message.toByteArray()
      exchange.responseHeaders.add("Content-Type", "text/plain")
      exchange.sendResponseHeaders(statusCode, response.size.toLong())
      val os: OutputStream = exchange.responseBody
      os.write(response)
      os.close()
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }
}