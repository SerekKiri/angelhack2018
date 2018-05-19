package io.github.feelfree.docugram.api.repository.login

import com.apollographql.apollo.ApolloClient
import io.github.feelfree.docugram.api.createApolloCall
import io.github.feelfree.docugram.api.mappers.mutations.LoginMutationMapper
import io.github.feelfree.docugram.models.LoginResponse
import io.github.feelfree.docugram.models.User
import io.github.feelfree.docugram.mutations.LoginMutation
import io.reactivex.Single

class LoginRepository(val apolloClient: ApolloClient) : LoginApi {
    override fun loginUser(email : String, password : String): Single<LoginResponse> {
        return createApolloCall(apolloClient.mutate(
                LoginMutation.builder()
                        .email(email)
                        .password(password)
                        .build()))
                .map { LoginMutationMapper.dataMapper(it) }
    }
}
