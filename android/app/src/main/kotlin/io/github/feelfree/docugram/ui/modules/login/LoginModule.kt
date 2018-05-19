package io.github.feelfree.docugram.ui.modules.login

import dagger.Module
import dagger.Provides
import io.github.feelfree.docugram.api.repository.login.LoginApi
import io.github.feelfree.docugram.base.Schedulers

@Module
class LoginModule {
    @Provides
    fun providePresenter(schedulers: Schedulers, loginApi: LoginApi) = LoginPresenter(schedulers, loginApi)
}