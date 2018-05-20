package io.github.feelfree.docugram.api.mappers

import io.github.feelfree.docugram.fragment.UserActionRequestFragment
import io.github.feelfree.docugram.models.Notification

val notificationMapper : Mapper<UserActionRequestFragment, Notification> = {
    Notification(it.id(), it.type().rawValue(), it.title(), it.content())
}