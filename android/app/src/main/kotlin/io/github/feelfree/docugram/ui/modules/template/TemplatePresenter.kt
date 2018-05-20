package io.github.feelfree.docugram.ui.modules.template

import io.github.feelfree.docugram.api.repository.submission.SubmissionApi
import io.github.feelfree.docugram.api.repository.template.TemplateApi
import io.github.feelfree.docugram.base.BasePresenter
import io.github.feelfree.docugram.base.Schedulers
import io.github.feelfree.docugram.fragment.DocumentTemplateFragment
import io.github.feelfree.docugram.models.fields.CheckboxField
import io.github.feelfree.docugram.models.fields.Field
import io.github.feelfree.docugram.models.fields.TextField

class TemplatePresenter(val schedulers : Schedulers, val templateApi: TemplateApi) : BasePresenter<TemplateView>() {
    lateinit var documentTemplateFragment : DocumentTemplateFragment

    fun loadTemplate(id : String) {
        compositeObservable.add(
                templateApi.getTemplateById(id)
                        .subscribeOn(schedulers.backgroundThread())
                        .observeOn(schedulers.mainThread())
                        .subscribe({
                            view?.showFields(it.first)
                            documentTemplateFragment = it.second
                        }, { view?.showErrorDialog(it) })
        )
    }

    fun sendSubmission(id : String, fields : List<Field>) {
        val checkboxesSubmission = arrayListOf<Boolean>()
        val textFieldSubmission = arrayListOf<String>()

        documentTemplateFragment.checkboxFields()!!.forEach {
            val fieldId = it.fragments().checkboxFieldFragment().id()
            checkboxesSubmission.add((fields.find { it.id == fieldId} as CheckboxField).value)
        }


        documentTemplateFragment.textFields()!!.forEach {
            val fieldId = it.fragments().textFieldFragment().id()
            textFieldSubmission.add((fields.find { it.id == fieldId} as TextField).value)
        }

        compositeObservable.add(
                templateApi.createSubmission(id, checkboxesSubmission.toList(), textFieldSubmission.toList())
                        .subscribeOn(schedulers.backgroundThread())
                        .observeOn(schedulers.mainThread())
                        .subscribe({ view?.close() }, { view?.showErrorDialog(it) })
        )

    }


}