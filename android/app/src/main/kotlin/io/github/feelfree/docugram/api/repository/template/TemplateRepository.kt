package io.github.feelfree.docugram.api.repository.template

import com.apollographql.apollo.ApolloClient
import io.github.feelfree.docugram.api.createApolloCall
import io.github.feelfree.docugram.api.mappers.mutations.GetDocumentTemplateByIdMapper
import io.github.feelfree.docugram.api.mappers.notificationMapper
import io.github.feelfree.docugram.api.mappers.templateMapper
import io.github.feelfree.docugram.fragment.DocumentTemplateFragment
import io.github.feelfree.docugram.models.Notification
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.mutations.CreateSubmissionMutation
import io.github.feelfree.docugram.query.GetDocumentTemplateByIdQuery
import io.github.feelfree.docugram.query.GetDocumentWorkflowTemplateByIdQuery
import io.github.feelfree.docugram.query.GetUserActionRequestTypesQuery
import io.github.feelfree.docugram.type.*
import io.reactivex.Single

class TemplateRepository(val apolloClient : ApolloClient) : TemplateApi {
    override fun getTemplateById(id: String): Single<Pair<Template, DocumentTemplateFragment>> {
        return createApolloCall(apolloClient.query(
                    GetDocumentTemplateByIdQuery.builder()
                            .id(id)
                            .build()))
                    .map { GetDocumentTemplateByIdMapper.templateMapper(it) }
    }

    override fun getNotificationsByUser(email: String): Single<List<Notification>> {
        return createApolloCall(apolloClient.query(
                GetUserActionRequestTypesQuery.builder()
                        .email(email)
                        .build()))
                .map { it.userActionRequests().map { notificationMapper(it.fragments().userActionRequestFragment()) } }
    }

    fun getStartNode(id : String) : Single<String> {
        return createApolloCall(apolloClient.query(GetDocumentWorkflowTemplateByIdQuery
                .builder()
                .id(id)
                .build()))
                .map { it.documentTemplate()!!.workflowNodes()!!.find { it.type() == WorkflowNodeType.ENTRY }!!.id() }
    }

    override fun createSubmission(templateId : String, checkBoxes : List<Boolean>, textFields : List<String>): Single<CreateSubmissionMutation.Data> {
        return createApolloCall(apolloClient.mutate(
                            CreateSubmissionMutation.builder()
                                    .checkboxFields(checkBoxes.createInput())
                                    .textFields(textFields.createInput())
                                    .template(templateId)
                                    .state("asdaisdoasfiasfu")
                                            .build())
                    )
    }

    fun List<Boolean>.createInput() : CheckboxFieldValueCreateManyInput {
        return CheckboxFieldValueCreateManyInput.builder()
                .create(map { CheckboxFieldValueCreateInput.builder().value(it).build() })
                .build()
    }

    fun List<String>.createInput() : TextFieldValueCreateManyInput {
        return TextFieldValueCreateManyInput.builder()
                .create(map { TextFieldValueCreateInput.builder().value(it).build() })
                .build()
    }
}