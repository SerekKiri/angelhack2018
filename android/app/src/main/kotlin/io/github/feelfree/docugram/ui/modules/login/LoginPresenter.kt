package io.github.feelfree.docugram.ui.modules.login

import io.github.feelfree.docugram.api.repository.login.LoginApi
import io.github.feelfree.docugram.base.BasePresenter
import io.github.feelfree.docugram.base.Schedulers

class LoginPresenter(val schedulers: Schedulers, val loginApi : LoginApi) : BasePresenter<LoginView>() {
    fun loginUser(email : String, password : String) {
        compositeObservable.add(
                loginApi.loginUser(email, password)
                        .subscribeOn(schedulers.backgroundThread())
                        .observeOn(schedulers.mainThread())
                        .subscribe({ view?.userLoggedIn(it.user, it.token) }, { view?.showErrorDialog(it) })
        )
    }
}