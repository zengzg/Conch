package zzg.conch.view.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import zzg.conch.R
import zzg.conch.presenter.IAccount
import zzg.conch.presenter.impl.IAccountImpl
import zzg.conch.view.ISignUpView

class SignUpActivity : AppCompatActivity(), ISignUpView {
  final val LOGTAG = SignUpActivity::class.qualifiedName

  var mProgress: ProgressBar? = null
  var etUsername: EditText? = null
  var etPassword: EditText? = null
  var btnSignUp: Button? = null

  val accountPresenter: IAccount = IAccountImpl()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)

    accountPresenter.attachedView = this

    mProgress = findViewById(R.id.sign_up_progress) as ProgressBar?
    etUsername = findViewById(R.id.username) as EditText?
    etPassword = findViewById(R.id.password) as EditText?
    btnSignUp = findViewById(R.id.sign_up_button) as Button?

    btnSignUp?.setOnClickListener {
      signUp(etUsername?.text.toString(), etPassword?.text.toString())
    }
  }

  override fun signUp(username: String, password: String) {
    accountPresenter.signUp(username, password)
  }

  override fun showSigningUpProgress() {
    mProgress?.visibility = View.VISIBLE
  }

  override fun dismissSigningUpProgress(message: String) {
    mProgress?.visibility = View.GONE
    if (!message.equals("")) {
      Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
      finish()
    }
  }
}
