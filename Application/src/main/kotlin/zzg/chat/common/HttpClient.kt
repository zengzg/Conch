package zzg.chat.common

import android.app.Application
import android.content.Context
import android.util.ArrayMap
import okhttp3.*
import java.io.File
import java.util.*

/**
 * A [OkHttpClient] singleton delegator.
 *
 * Created by 曾志刚 on 16-2-16.
 */
object HttpClient {
  // the delegate
  var client: OkHttpClient? = null
  private var context: Context? = null

  /**
   * Invoke this method in [Application] onCreate().
   *
   * @param context must be the application context.
   */
  fun init(context: Context) {
    if (context !is Application)
      throw IllegalArgumentException("the context must be application context!")

    this.context = context

    client = OkHttpClient.Builder()
        .cache(cacheInit())
        .cookieJar(ActiveCookieJar())
        .addInterceptor {
          // add an empty interceptor
          it.proceed(it.request())
        }
        .addNetworkInterceptor {
          it.proceed(it.request())
        }
        .build()
  }

  private fun cacheInit(): Cache {
    var cache = File(context!!.cacheDir.path + "/okhttp")
    if (!cache.isDirectory) {
      var success = cache.mkdirs()

      if (!success) {
        throw RuntimeException("Oops,it can`t create okhttp cache directory.")
      }
    }
    return Cache(cache, 1024 * 1024 * 10)
  }


  /*
   * A Application lifetime cookie store.
   */
  private class ActiveCookieJar : CookieJar {
    val cookies = ArrayMap<HttpUrl, MutableList<Cookie>>()

    override fun saveFromResponse(p0: HttpUrl?, p1: MutableList<Cookie>?) {
      if (p1 != null && p1.size > 0) {
        cookies.put(p0, p1)
      }
    }

    override fun loadForRequest(p0: HttpUrl?): MutableList<Cookie>? =
        cookies[p0] ?: Collections.emptyList()
  }

  /*
   * A persist cookie store,save the cookies into the android preference.
   *
   * TODO implement PersistenceCookieJar
   */
  private class PersistenceCookieJar : CookieJar {

    override fun saveFromResponse(p0: HttpUrl?, p1: MutableList<Cookie>?) {
      throw UnsupportedOperationException()
    }

    override fun loadForRequest(p0: HttpUrl?): MutableList<Cookie>? {
      throw UnsupportedOperationException()
    }

  }
}