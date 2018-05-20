package io.github.feelfree.docugram.api.mappers.mutations

import io.github.feelfree.docugram.api.mappers.FieldMapper
import io.github.feelfree.docugram.api.mappers.Mapper
import io.github.feelfree.docugram.fragment.DocumentTemplateFragment
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.models.fields.CheckboxField
import io.github.feelfree.docugram.models.fields.Field
import io.github.feelfree.docugram.models.fields.MarkdownField
import io.github.feelfree.docugram.models.fields.TextField
import io.github.feelfree.docugram.query.GetDocumentTemplateByIdQuery

class GetDocumentTemplateByIdMapper {
    companion object {
        val templateMapper : Mapper<GetDocumentTemplateByIdQuery.Data, Pair<Template, DocumentTemplateFragment>> = {
            val documentTemplateFragment = it.documentTemplate()!!.fragments().documentTemplateFragment()

            val checkBoxes = documentTemplateFragment.checkboxFields()!!.map { FieldMapper.checkboxMapper(it.fragments().checkboxFieldFragment()) }
            val textFields = documentTemplateFragment.textFields()!!.map { FieldMapper.textFieldMapper(it.fragments().textFieldFragment()) }
            val markdownFields = documentTemplateFragment.markdownFields()!!.map { FieldMapper.markdownFieldMapper(it.fragments().markdownFieldFragment()) }


            val fields = documentTemplateFragment.fieldHeaders()?.map {
                val fragment = it.fragments().fieldHeaderFragment()
                var predict = checkBoxes.find { it.id == fragment.definitionId() } as Field?
                if (predict == null) {
                    predict = textFields.find { it.id == fragment.definitionId() }
                }

                if (predict == null) {
                    predict = markdownFields.find { it.id == fragment.definitionId() }
                }
                predict!!
            }
            Pair(Template(documentTemplateFragment.id(), fields ?: emptyList<Field>()), documentTemplateFragment)
        }
    }
}