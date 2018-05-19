package io.github.feelfree.docugram.ui.adapter.viewholders

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import io.github.feelfree.docugram.models.fields.TextField
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.textfield.*

class TextFieldViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(field : TextField) {
        editText.setText(field.value)
        titleTextView.text = field.label
        editText.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        s?.let {
                            field.value = s.toString()
                        }
                    }
                }
        )
    }
}
