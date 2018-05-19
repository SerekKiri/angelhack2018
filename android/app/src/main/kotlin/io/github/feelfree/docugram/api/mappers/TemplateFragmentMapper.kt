package io.github.feelfree.docugram.api.mappers

import io.github.feelfree.docugram.fragment.DocumentTemplateFragment
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.models.fields.Field
import io.github.feelfree.docugram.query.GetDocumentTemplateByIdQuery

val templateMapper : Mapper<DocumentTemplateFragment, Template> = {

    val checkBoxes = it.checkboxFields()!!.map { FieldMapper.checkboxMapper(it.fragments().checkboxFieldFragment()) }
    val textFields = it.textFields()!!.map { FieldMapper.textFieldMapper(it.fragments().textFieldFragment()) }
    val markdownFields = it.markdownFields()!!.map { FieldMapper.markdownFieldMapper(it.fragments().markdownFieldFragment()) }


    val fields = it.fieldHeaders()?.map {
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
    Template(it.id(), fields ?: emptyList<Field>())
}