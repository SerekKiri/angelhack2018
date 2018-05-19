package io.github.feelfree.docugram.ui.modules.template

import dagger.Module
import dagger.Provides
import io.github.feelfree.docugram.api.repository.submission.SubmissionApi
import io.github.feelfree.docugram.api.repository.template.TemplateApi
import io.github.feelfree.docugram.base.Schedulers

@Module
class TemplateModule {
    @Provides
    fun provideTemplatePresenter(schedulers : Schedulers, templateApi: TemplateApi) = TemplatePresenter(schedulers, templateApi)
}