package io.github.feelfree.docugram.ui.adapter.viewholders

import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfree.docugram.models.Notification
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.approval.*

class ApprovalViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    companion object {
        const val RAW_APPROVAL = "APPROVAL"
    }

    fun bind(notifi : Notification, listener : () -> Unit) {
        title.text = notifi.title
        content.text = notifi.content

        approve.setOnClickListener {
            listener()
        }

        //approve.background.setColorFilter(0x98fb98, PorterDuff.Mode.MULTIPLY)
        disapprove.setOnClickListener {
            listener()
        }
    }

}
