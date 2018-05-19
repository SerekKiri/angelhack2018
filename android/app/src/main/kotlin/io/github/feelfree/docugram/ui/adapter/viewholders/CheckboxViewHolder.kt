package io.github.feelfree.docugram.ui.adapter.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfree.docugram.models.fields.CheckboxField
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.checkboxfield.*

class CheckboxViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(field : CheckboxField) {
        checkbox.text = field.label
        checkbox.isChecked = field.value
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            field.value = isChecked
        }
    }
}
