package io.github.feelfree.docugram.ui.adapter.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfree.docugram.models.Notification
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.editrequest.*

class EditRequestViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    companion object {
        val RAW_EDITREQUEST = "EDIT_REQUEST"
    }

    fun bind(notifi : Notification) {
        title.text = notifi.title
        content.text = notifi.content

        edit.setOnClickListener {

        }
    }

}
