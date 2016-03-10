package zzg.conch.model

/**
 * User information
 *
 * Created by 曾志刚 on 16-2-17.
 */
class User(username: String, password: String) {
  var uid: String? = null
  var username: String
  var password: String

  init {
    this.username = username
    this.password = password
  }

}