package io.github.feelfree.docugram.ui.modules.template

import io.github.feelfree.docugram.api.repository.submission.SubmissionApi
import io.github.feelfree.docugram.base.BasePresenter
import io.github.feelfree.docugram.base.Schedulers

class TemplatePresenter(val schedulers : Schedulers, val templateApi: SubmissionApi) : BasePresenter<TemplateView>() {

}