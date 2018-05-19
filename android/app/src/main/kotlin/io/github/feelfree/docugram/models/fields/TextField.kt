package io.github.feelfree.docugram.models.fields

data class TextField(val id : String, val label : String, val defaultValue : String, var value : String = defaultValue) : Field