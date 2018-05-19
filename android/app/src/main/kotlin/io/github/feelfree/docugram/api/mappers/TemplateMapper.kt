package io.github.feelfree.docugram.api.mappers

import io.github.feelfree.docugram.models.Template

val templateMapper : Mapper<String, Template> = {
    Template()
}