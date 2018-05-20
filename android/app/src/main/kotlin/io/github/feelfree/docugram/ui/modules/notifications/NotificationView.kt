package io.github.feelfree.docugram.ui.modules.notifications

import io.github.feelfree.docugram.base.BaseView
import io.github.feelfree.docugram.models.Notification

interface NotificationView : BaseView {
    fun showNotifications(notification : List<Notification>)
}