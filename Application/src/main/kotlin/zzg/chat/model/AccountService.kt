package zzg.chat.model

import com.firebase.client.Firebase
import rx.Observable
import rx.lang.kotlin.observable

/**
 * backend for account manager.
 *
 * Created by 曾志刚 on 16-3-9.
 */
object AccountService {

  var rootRef: Firebase =
      Firebase("https://vivid-inferno-8287.firebaseio.com/account");

  var accountRef = rootRef.child("users")

  fun signUp(user: User): Observable<User> {
    return observable {
      accountRef.push().setValue(user)
    }
  }

}