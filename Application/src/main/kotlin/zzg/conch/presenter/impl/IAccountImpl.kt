package zzg.conch.presenter.impl

import android.util.Log
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zzg.conch.model.AccountService
import zzg.conch.model.User
import zzg.conch.presenter.IAccount
import zzg.conch.view.ISignUpView

/**
 * Account manager impl.
 *
 * Created by 曾志刚 on 16-2-17.
 */
class IAccountImpl : IAccount {
  override var attachedView: ISignUpView? = null

  override fun signUp(username: String, password: String) {
    attachedView?.showSigningUpProgress()
    AccountService.signUp(User(username, password))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(SignUpObserver())
  }

  override fun fetchUser() {
    throw UnsupportedOperationException()
  }

  private inner class SignUpObserver : Observer<User> {
    override fun onNext(user: User) {
      attachedView?.dismissSigningUpProgress()
    }

    override fun onError(error: Throwable?) {
      attachedView?.dismissSigningUpProgress(error.toString())
    }

    override fun onCompleted() {
    }

  }
}