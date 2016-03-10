package zzg.conch.presenter

import zzg.conch.view.ISignUpView

/**
 * This is for manage account information.
 *
 * features:
 *
 * 1.sign up.
 *
 * 2.sign in.
 *
 * 3.get user info.
 *
 * 4.get friend list with paging.
 *
 * 5.add friend.
 *
 * Created by 曾志刚 on 16-2-17.
 */
interface IAccount {
  var attachedView: ISignUpView?

  fun fetchUser()

  fun signUp(username: String, password: String)

  //  fun signIn()
}