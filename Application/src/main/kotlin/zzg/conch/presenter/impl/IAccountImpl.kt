package zzg.conch.presenter.impl

import android.util.Log
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zzg.conch.model.AccountService
import zzg.conch.model.User
import zzg.conch.presenter.IAccount

/**
 * Account manager impl.
 *
 * Created by 曾志刚 on 16-2-17.
 */
class IAccountImpl : IAccount {

  override fun signUp(username: String, password: String) {
    AccountService.signUp(User(username, password))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          Log.i("info", "" + it.id)
        }
  }

  override fun fetchUser() {
    throw UnsupportedOperationException()
  }

}