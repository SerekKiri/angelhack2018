package io.github.feelfree.docugram.models.fields

data class CheckboxField(override val id: String, val label: String, val defaultValue: Boolean, var value : Boolean = defaultValue) : Field