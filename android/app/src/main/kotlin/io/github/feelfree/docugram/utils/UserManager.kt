package io.github.feelfree.docugram.utils

import io.github.feelfree.docugram.models.User

interface UserManagerApi {
    val isUserLoggedIn : Boolean
    fun loginUser(user : User, token : String)
    fun logoutUser()
}

class UserManager(val credentialPreferencesApi: CredentialPreferencesApi) : UserManagerApi {
    override fun loginUser(user: User, token: String) {
        with(credentialPreferencesApi) {
            name = user.name
            email = user.email
            this.token = token
        }
    }

    override fun logoutUser() {
        with(credentialPreferencesApi) {
            name = ""
            email = ""
            this.token = ""
        }
    }

    override val isUserLoggedIn: Boolean
        get() = !credentialPreferencesApi.token.isNullOrEmpty()

}