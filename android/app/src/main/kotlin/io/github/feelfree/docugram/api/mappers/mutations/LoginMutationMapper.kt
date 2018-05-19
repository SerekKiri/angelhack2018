package io.github.feelfree.docugram.api.mappers.mutations

import io.github.feelfree.docugram.api.mappers.Mapper
import io.github.feelfree.docugram.api.mappers.userMapper
import io.github.feelfree.docugram.models.LoginResponse
import io.github.feelfree.docugram.mutations.LoginMutation

class LoginMutationMapper {
    companion object {
        val dataMapper: Mapper<LoginMutation.Data, LoginResponse> = {
            LoginResponse(
                    userMapper(it.login().user().fragments().userFragment()),
                    it.login().token())
        }
    }
}