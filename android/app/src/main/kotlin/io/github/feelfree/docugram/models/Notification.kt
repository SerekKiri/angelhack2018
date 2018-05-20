package io.github.feelfree.docugram.models

data class Notification(
        val id : String,
        val type : String,
        val title : String,
        val content : String
)