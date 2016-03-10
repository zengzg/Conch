package zzg.conch.model

import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import rx.Observable
import rx.lang.kotlin.observable

/**
 * backend for account manager.
 *
 * Created by 曾志刚 on 16-3-9.
 */
object AccountService {
  const val SIGN_UP_TYPE_EMAIL = 0
  const val SIGN_UP_TYPE_GOOGLE = 1 // not use now
  const val SIGN_UP_TYPE_GITHUB = 2 // not use now

  var rootRef: Firebase =
      Firebase("https://vivid-inferno-8287.firebaseio.com");

  /**
   * Sign up to cloud
   *
   * @param type sign up type,include email&password and google(actual not impl).
   */
  fun signUp(user: User, type: Int = SIGN_UP_TYPE_EMAIL): Observable<User> {
    when (type) {
      SIGN_UP_TYPE_EMAIL -> {
        return observable {
          class TheHandler : Firebase.ValueResultHandler<Map<String, Any>> {
            override fun onSuccess(result: Map<String, Any>?) {
              user.uid = result?.get("uid").toString()
              it.onNext(user)
              it.onCompleted()
            }

            override fun onError(error: FirebaseError?) {
              it.onError(Throwable(error.toString()))
            }
          }
          rootRef.createUser(user.username, user.password, TheHandler())
        }
      }
      else -> {
        throw IllegalArgumentException("Google account sign up is not allowed yet.")
      }
    }
  }


}