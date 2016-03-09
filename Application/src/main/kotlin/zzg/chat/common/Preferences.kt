package zzg.chat.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * A simple [SharedPreferences] util.
 *
 * Created by 曾志刚 on 16-2-16.
 */
object Preferences {

  private var context: Context? = null
  private var spf: SharedPreferences? = null

  /**
   * Invoke this method in [Application] onCreate().
   *
   * @param context must be the application context.
   */
  fun init(context: Context) {
    if (context !is Application)
      throw IllegalArgumentException("the context must be application context!")

    this.context = context

    initPreference()
  }

  // create a private application lifetime preference named GLOBAL.
  private fun initPreference() {
    spf = context!!.getSharedPreferences("GLOBAL", Context.MODE_PRIVATE)
  }

  /**
   * Cache the key-value.
   */
  fun save(key: String, value: String) {
    spf!!.edit().putString(key, value).apply()
  }

  /**
   * Get the cached value with the given key.
   */
  fun fetch(key: String): String = spf!!.getString(key, "")

  /**
   * A async way to get cached data.
   */
  fun fetch(key: String, callback: Action1<String>) {
    Observable.just(fetch(key))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(callback)
  }
}