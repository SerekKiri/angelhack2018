package io.github.feelfree.docugram.api.mappers

import io.github.feelfree.docugram.fragment.UserFragment
import io.github.feelfree.docugram.models.User

val userMapper : Mapper<UserFragment, User> = {
    User(it.id(), it.email(), it.email())
}