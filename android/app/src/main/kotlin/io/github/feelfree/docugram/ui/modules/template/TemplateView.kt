package io.github.feelfree.docugram.ui.modules.template

import io.github.feelfree.docugram.base.BaseView
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.models.fields.Field

interface TemplateView : BaseView {
    fun showFields(template : Template)
}