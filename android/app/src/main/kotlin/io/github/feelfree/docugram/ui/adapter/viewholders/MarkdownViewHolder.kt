package io.github.feelfree.docugram.ui.adapter.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfree.docugram.models.fields.MarkdownField
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.markdownfield.*

class MarkdownViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindView(field : MarkdownField) {
        markdown.markdown = field.content
    }
}
