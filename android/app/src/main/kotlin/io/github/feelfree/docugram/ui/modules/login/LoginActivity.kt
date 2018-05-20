package io.github.feelfree.docugram.ui.modules.login

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.base.BaseActivity
import io.github.feelfree.docugram.models.User
import io.github.feelfree.docugram.ui.modules.NavigatorApi
import io.github.feelfree.docugram.utils.UserManagerApi
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {
    companion object {
        fun createIntent(activity: Activity) : Intent {
            return Intent(activity, LoginActivity::class.java)
        }
    }
    @Inject
    lateinit var presenter : LoginPresenter

    @Inject
    lateinit var navigatorApi: NavigatorApi

    @Inject
    lateinit var userManagerApi : UserManagerApi

    val progressDialog by lazy {
        ProgressDialog.show(this, getString(R.string.login), getString(R.string.logging_in), true)
    }

    override fun userLoggedIn(user: User, token: String) {
        userManagerApi.loginUser(user, token)
        progressDialog.dismiss()
        navigatorApi.openMainActivity(this)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.subscribe(this)

        if (userManagerApi.isUserLoggedIn) {
            navigatorApi.openMainActivity(this)
            finish()
        }

        loginButton.setOnClickListener {
            verifyData()
        }
    }

    fun verifyData() {
        if (passwordEditText.text.isEmpty()) {
            passwordEditTextTitle.error = getString(R.string.field_required)
            return
        }
        if (emailEditText.text.isEmpty()) {
            emailEditTextTitle.error =  getString(R.string.field_required)
            return
        }

        progressDialog.isIndeterminate = true
        presenter.loginUser(emailEditText.text.toString(), passwordEditText.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showErrorDialog(e: Throwable) {
        progressDialog.dismiss()
        super.showErrorDialog(e)
    }
}