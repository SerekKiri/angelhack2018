package io.github.feelfree.docugram.ui.modules.login

import io.github.feelfree.docugram.base.BaseView
import io.github.feelfree.docugram.models.User

interface LoginView : BaseView {
    fun userLoggedIn(user : User, token : String)
}