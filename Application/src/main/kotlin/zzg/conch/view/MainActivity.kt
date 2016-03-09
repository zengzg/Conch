package zzg.conch.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import zzg.conch.R
import zzg.conch.model.User
import zzg.conch.presenter.IAccount
import zzg.conch.presenter.impl.IAccountImpl

class MainActivity : AppCompatActivity() {

  private val accountPresenter: IAccount = IAccountImpl()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val toolbar = findViewById(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)

    val fab = findViewById(R.id.fab) as FloatingActionButton
    fab.setOnClickListener {
      var user: User = User("yuna", "123456")
    }
  }
}
