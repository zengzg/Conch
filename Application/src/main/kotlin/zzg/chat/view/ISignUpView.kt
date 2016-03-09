package zzg.chat.view

/**
 * Sign up view.
 *
 * features:
 *
 * 1.sign up.
 *
 * 2.show signing up progress.
 *
 * 3.show sign up result.
 *
 * 4.back to pre view with sign up result.
 *
 * Created by 曾志刚 on 16-3-9.
 */
interface ISignUpView {

  fun signUp(username: String, password: String)

  fun showSigningUpProgress()

}