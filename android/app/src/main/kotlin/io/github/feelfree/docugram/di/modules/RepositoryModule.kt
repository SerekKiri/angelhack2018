package io.github.feelfree.docugram.di.modules

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.github.feelfree.docugram.api.repository.login.LoginApi
import io.github.feelfree.docugram.api.repository.login.LoginRepository
import io.github.feelfree.docugram.api.repository.submission.SubmissionApi
import io.github.feelfree.docugram.api.repository.submission.SubmissionRepository

@Module
class RepositoryModule {
    @Provides
    fun provideSubmissionApi(apolloClient: ApolloClient) : SubmissionApi = SubmissionRepository(apolloClient)

    @Provides
    fun provideLoginApi(apolloClient: ApolloClient) : LoginApi = LoginRepository(apolloClient)
}