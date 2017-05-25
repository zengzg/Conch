package zzg.conch

import android.app.Application
import android.os.Environment
import com.firebase.client.Firebase
import zzg.conch.common.HttpClient
import zzg.conch.common.Preferences

/**
 * The Android Application component.
 *
 * features:
 *
 * 1.Initial okhttp client.
 *
 * 2.Initial a preference to save simple data.
 *
 * Created by 曾志刚 on 16-2-16.
 */
class TheApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    // init firebase
    Firebase.setAndroidContext(this)

    // init okhttp client.
    HttpClient.init(this)

    // init preference
    Preferences.init(this)
  }
}