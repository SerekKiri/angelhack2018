package io.github.feelfree.docugram.models

import io.github.feelfree.docugram.models.fields.Field

data class Template(val id : String,
                    val fields : List<Field>)