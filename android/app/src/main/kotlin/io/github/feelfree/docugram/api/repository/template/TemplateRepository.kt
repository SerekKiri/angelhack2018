package io.github.feelfree.docugram.api.repository.template

import com.apollographql.apollo.ApolloClient
import io.github.feelfree.docugram.api.createApolloCall
import io.github.feelfree.docugram.api.mappers.mutations.GetDocumentTemplateByIdMapper
import io.github.feelfree.docugram.api.mappers.templateMapper
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.query.GetDocumentTemplateByIdQuery
import io.reactivex.Single

class TemplateRepository(val apolloClient : ApolloClient) : TemplateApi {
    override fun getTemplateById(id: String): Single<Template> {
        return createApolloCall(apolloClient.query(
                    GetDocumentTemplateByIdQuery.builder()
                            .id(id)
                            .build()))
                    .map { templateMapper(it.documentTemplate()!!.fragments().documentTemplateFragment()) }
    }

}