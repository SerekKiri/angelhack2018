package io.github.feelfree.docugram.api.repository.template

import io.github.feelfree.docugram.models.Notification
import io.github.feelfree.docugram.models.Template
import io.reactivex.Single

interface TemplateApi {
    fun getTemplateById(id : String) : Single<Template>
    fun getNotificationsByUser(email : String) : Single<List<Notification>>
}