

package _4Tipsy.SodaCli.utils


import okhttp3.OkHttpClient
import okhttp3.Request

import java.net.ConnectException

// modules
import _4Tipsy.SodaCli.Config




fun isSodaAlive(): Boolean {

  val request = Request.Builder()
                .url("http://localhost:${Config.Instance.port}")
                .build()


  try {

    val response = OkHttpClient().newCall(request).execute()
    return response.isSuccessful

  } catch (e: ConnectException) {
    return false
  }
}