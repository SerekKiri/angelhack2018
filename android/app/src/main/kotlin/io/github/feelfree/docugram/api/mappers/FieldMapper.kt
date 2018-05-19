package io.github.feelfree.docugram.api.mappers

import io.github.feelfree.docugram.fragment.CheckboxFieldFragment
import io.github.feelfree.docugram.fragment.MarkdownFieldFragment
import io.github.feelfree.docugram.fragment.TextFieldFragment
import io.github.feelfree.docugram.models.fields.CheckboxField
import io.github.feelfree.docugram.models.fields.MarkdownField
import io.github.feelfree.docugram.models.fields.TextField

class FieldMapper {
    companion object {
        val checkboxMapper : Mapper<CheckboxFieldFragment, CheckboxField> = {
            CheckboxField(it.id(), it.label(), it.defaultValue())
        }

        val textFieldMapper : Mapper<TextFieldFragment, TextField> = {
            TextField(it.id(), it.label(), it.defaultValue())
        }

        val markdownFieldMapper : Mapper<MarkdownFieldFragment, MarkdownField> = {
            MarkdownField(it.id(), it.content())
        }
    }
}