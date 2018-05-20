package io.github.feelfree.docugram.ui.modules.notifications

import io.github.feelfree.docugram.api.repository.template.TemplateApi
import io.github.feelfree.docugram.base.BasePresenter
import io.github.feelfree.docugram.base.Schedulers

class NotificationPresenter(val schedulers: Schedulers, val templateApi : TemplateApi) : BasePresenter<NotificationView>() {
    fun getNotifications(email : String) {
        compositeObservable.add(
                templateApi.getNotificationsByUser(email)
                        .subscribeOn(schedulers.backgroundThread())
                        .observeOn(schedulers.mainThread())
                        .subscribe({ view?.showNotifications(it) }, { view?.showErrorDialog(it) })
        )
    }
}