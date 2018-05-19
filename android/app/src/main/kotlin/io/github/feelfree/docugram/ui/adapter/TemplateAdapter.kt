package io.github.feelfree.docugram.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.ui.adapter.viewholders.TemplateViewHolder
import io.github.feelfree.docugram.utils.inflate
import javax.inject.Inject

// We're injecting constructor here. All viewholder dependencies can get them from here.
class TemplateAdapter @Inject constructor() : RecyclerView.Adapter<TemplateViewHolder>() {
    val templates = arrayListOf<Template>()
    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        holder.apply {
            bindView(templates[position])
        }
    }

    fun showBoards(repos : List<Template>) {
        templates.apply {
            clear()
            addAll(repos)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder =
            TemplateViewHolder(parent.inflate(R.layout.template_item))
    override fun getItemCount(): Int = templates.size
}