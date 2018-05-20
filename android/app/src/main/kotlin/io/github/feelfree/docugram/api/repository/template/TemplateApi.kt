package io.github.feelfree.docugram.api.repository.template

import io.github.feelfree.docugram.fragment.DocumentTemplateFragment
import io.github.feelfree.docugram.models.Notification
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.mutations.CreateSubmissionMutation
import io.reactivex.Single

interface TemplateApi {
    fun getTemplateById(id : String) : Single<Pair<Template, DocumentTemplateFragment>>
    fun getNotificationsByUser(email : String) : Single<List<Notification>>
    fun createSubmission(templateId: String, checkBoxes: List<Boolean>, textFields: List<String>): Single<CreateSubmissionMutation.Data>
}