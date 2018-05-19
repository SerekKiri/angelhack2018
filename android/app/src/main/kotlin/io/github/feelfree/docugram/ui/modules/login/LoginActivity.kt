package io.github.feelfree.docugram.ui.modules.login

import android.os.Bundle
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.base.BaseActivity
import io.github.feelfree.docugram.models.User
import io.github.feelfree.docugram.utils.UserManagerApi
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {
    @Inject
    lateinit var presenter : LoginPresenter

    @Inject
    lateinit var userManagerApi : UserManagerApi

    override fun userLoggedIn(user: User, token: String) {
        userManagerApi.loginUser(user, token)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.subscribe(this)

        loginButton.setOnClickListener {
            verifyData()
        }
    }

    fun verifyData() {
        if (passwordEditText.text.isEmpty()) {
            passwordEditTextTitle.error = "Pole wymagane"
            return
        }
        if (emailEditText.text.isEmpty()) {
            emailEditTextTitle.error = "Pole wymagane"
            return
        }

        presenter.loginUser(emailEditText.text.toString(), passwordEditText.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}