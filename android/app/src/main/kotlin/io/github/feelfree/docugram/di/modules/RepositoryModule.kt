package io.github.feelfree.docugram.di.modules

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.github.feelfree.docugram.api.repository.login.LoginApi
import io.github.feelfree.docugram.api.repository.login.LoginRepository
import io.github.feelfree.docugram.api.repository.submission.SubmissionApi
import io.github.feelfree.docugram.api.repository.submission.SubmissionRepository
import io.github.feelfree.docugram.api.repository.template.TemplateApi
import io.github.feelfree.docugram.api.repository.template.TemplateRepository

@Module
class RepositoryModule {
    @Provides
    fun provideSubmissionApi(apolloClient: ApolloClient) : SubmissionApi = SubmissionRepository(apolloClient)

    @Provides
    fun provideLoginApi(apolloClient: ApolloClient) : LoginApi = LoginRepository(apolloClient)

    @Provides
    fun provideTemplateApi(apolloClient: ApolloClient) : TemplateApi = TemplateRepository(apolloClient)
}