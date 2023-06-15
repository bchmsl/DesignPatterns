package com.space.designpatterns.behavioral

/** State to check if user is [Unauthorized] or [Authorized] */
sealed class UserState
object Unauthorized : UserState()
data class Authorized(val username: String, val userToken: String) : UserState()

/** Manager that uses [UserState] to handle specific events */
class UserManager {
    private var userState: UserState = Unauthorized

    fun logIn(username: String) {
        userState = Authorized(username, username.hashCode().toString())
    }

    fun logOut() {
        userState = Unauthorized
    }

    fun getUserToken() =
        if (userState is Authorized) (userState as Authorized).userToken else ""
}

fun main() {
    val manager = UserManager()
    manager.logIn("loremIpsum")
    println(manager.getUserToken())
    manager.logOut()
}

