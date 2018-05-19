package io.github.feelfree.docugram.ui.adapter.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfree.docugram.models.Template
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_item.*

// Use layoutContainer to cache views. It improves scroll performance
class TemplateViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindView(template : Template) {
    }
}