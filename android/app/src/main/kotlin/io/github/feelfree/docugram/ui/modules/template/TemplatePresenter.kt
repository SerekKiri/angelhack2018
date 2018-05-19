package io.github.feelfree.docugram.ui.modules.template

import io.github.feelfree.docugram.api.repository.submission.SubmissionApi
import io.github.feelfree.docugram.api.repository.template.TemplateApi
import io.github.feelfree.docugram.base.BasePresenter
import io.github.feelfree.docugram.base.Schedulers

class TemplatePresenter(val schedulers : Schedulers, val templateApi: TemplateApi) : BasePresenter<TemplateView>() {
    fun loadTemplate(id : String) {
        compositeObservable.add(
                templateApi.getTemplateById(id)
                        .subscribeOn(schedulers.backgroundThread())
                        .observeOn(schedulers.mainThread())
                        .subscribe({ view?.showFields(it) }, { view?.showErrorDialog(it) })
        )
    }
}