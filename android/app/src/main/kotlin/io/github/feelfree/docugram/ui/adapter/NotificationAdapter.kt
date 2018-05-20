package io.github.feelfree.docugram.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.models.Notification
import io.github.feelfree.docugram.ui.adapter.viewholders.ApprovalViewHolder
import io.github.feelfree.docugram.ui.adapter.viewholders.EditRequestViewHolder
import io.github.feelfree.docugram.ui.adapter.viewholders.NotificationViewHolder
import io.github.feelfree.docugram.utils.inflate
import javax.inject.Inject

class NotificationAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val notifications = arrayListOf<Notification>()
    companion object {
        const val EDITREQUEST = 0
        const val NOTIFICATION = 1
        const val APPROVAL = 2
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is EditRequestViewHolder -> holder.bind(notifications[position])
            is NotificationViewHolder -> holder.bind(notifications[position])
            is ApprovalViewHolder -> holder.bind(notifications[position], {
                notifications.removeAt(position)
                notifyDataSetChanged()
            })
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(notifications[position].type) {
            ApprovalViewHolder.RAW_APPROVAL -> APPROVAL
            EditRequestViewHolder.RAW_EDITREQUEST -> EDITREQUEST
            else -> NOTIFICATION
        }
    }

    fun showNotifications(data : List<Notification>) {
        notifications.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EDITREQUEST -> EditRequestViewHolder(parent.inflate(R.layout.editrequest))
            NOTIFICATION -> NotificationViewHolder(parent.inflate(R.layout.notification))
            else -> ApprovalViewHolder(parent.inflate(R.layout.approval))
        }
    }
    override fun getItemCount(): Int = notifications.size
}