package io.github.feelfree.docugram.ui.adapter.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfree.docugram.models.Notification
import io.github.feelfree.docugram.models.fields.SubmitField
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notification.*
import kotlinx.android.synthetic.main.submitbutton.*

class SubmitViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(submitField : SubmitField, submitListener : () -> Unit) {
        submitbutton.setOnClickListener {
            submitListener.invoke()
        }
    }

}
