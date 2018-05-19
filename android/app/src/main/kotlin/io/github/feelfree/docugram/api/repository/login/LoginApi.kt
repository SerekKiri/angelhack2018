package io.github.feelfree.docugram.api.repository.login

import io.github.feelfree.docugram.models.LoginResponse
import io.github.feelfree.docugram.models.User
import io.reactivex.Single

interface LoginApi {
    fun loginUser(email : String, password : String) : Single<LoginResponse>
}