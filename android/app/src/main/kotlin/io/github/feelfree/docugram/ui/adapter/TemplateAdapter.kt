package io.github.feelfree.docugram.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.models.fields.CheckboxField
import io.github.feelfree.docugram.models.fields.Field
import io.github.feelfree.docugram.models.fields.MarkdownField
import io.github.feelfree.docugram.models.fields.TextField
import io.github.feelfree.docugram.ui.adapter.viewholders.CheckboxViewHolder
import io.github.feelfree.docugram.ui.adapter.viewholders.MarkdownViewHolder
import io.github.feelfree.docugram.ui.adapter.viewholders.TemplateViewHolder
import io.github.feelfree.docugram.ui.adapter.viewholders.TextFieldViewHolder
import io.github.feelfree.docugram.utils.inflate
import javax.inject.Inject

// We're injecting constructor here. All viewholder dependencies can get them from here.
class TemplateAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val fields = arrayListOf<Field>()
    companion object {
        const val CHECKBOX = 0
        const val TEXTFIELD = 1
        const val MARKDOWN = 2
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CheckboxViewHolder -> holder.bind(fields[position] as CheckboxField)
            is TextFieldViewHolder -> holder.bind(fields[position] as TextField)
            is MarkdownViewHolder -> holder.bindView(fields[position] as MarkdownField)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(fields[position]) {
            is CheckboxField -> CHECKBOX
            is TextField -> TEXTFIELD
            is MarkdownField -> MARKDOWN
            else -> 0
        }
    }

    fun showFields(repos : List<Field>) {
        fields.apply {
            clear()
            addAll(repos)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CHECKBOX -> CheckboxViewHolder(parent.inflate(R.layout.checkboxfield))
            TEXTFIELD -> TextFieldViewHolder(parent.inflate(R.layout.textfield))
            else -> MarkdownViewHolder(parent.inflate(R.layout.markdownfield))
        }
    }
    override fun getItemCount(): Int = fields.size
}