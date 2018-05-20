package io.github.feelfree.docugram.ui.modules.notifications

import dagger.Module
import dagger.Provides
import io.github.feelfree.docugram.api.repository.template.TemplateApi
import io.github.feelfree.docugram.base.Schedulers

@Module
class NotificationModule{
    @Provides
    fun providePresenter(schedulers: Schedulers, templateApi: TemplateApi) = NotificationPresenter(schedulers, templateApi)
}