
package _4Tipsy.SodaCli


import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody


// modules
import _4Tipsy.SodaCli.Config




object Requester {



  fun handleTask(task: String): String {

    val jsonBody = Gson().toJson(mapOf("task" to task))
    return makeRequest(jsonBody)
  }


  fun handleTask(task: String, id: Int): String {
    val jsonBody = Gson().toJson(mapOf("task" to task, "id" to id))
    return makeRequest(jsonBody)
  }


  fun handleTask(task: String, initiatorPath: String): String {
    val jsonBody = Gson().toJson(mapOf("task" to task, "initiatorPath" to initiatorPath))
    return makeRequest(jsonBody)
  }






  private fun makeRequest(jsonBody: String): String {

    val body = jsonBody.toRequestBody("application/json".toMediaType())

    val client = OkHttpClient()
    val request = Request.Builder()
                  .url("http://localhost:${Config.Instance.port}/soda-task")
                  .post(body)
                  .build()


    val response = client.newCall(request).execute()
    return response.body!!.string()
  }

}