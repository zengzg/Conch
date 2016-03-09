package zzg.chat.model

/**
 * User information
 *
 * Created by 曾志刚 on 16-2-17.
 */
class User(username: String, password: String) {
  var username: String
  var password: String

  var id:Int = -1

  init {
    this.username = username
    this.password = password
  }

}