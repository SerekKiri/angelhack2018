<template>
    <v-container v-if="!!documentTemplate">
        <h1>Create new submission: {{documentTemplate.name}}</h1>
        <div v-for="(fieldHeader, fieldIndex) in documentTemplate.fieldHeaders"
             :key="fieldHeader.id"
             class="pa-2">
            <component :is="fieldComponents[fieldHeader.type]"
                       v-bind="fieldProps(fieldHeader)"
                       v-model="fieldValue(fieldHeader).value"
                       :editorMode="false" />
        </div>
        <v-fab-transition>
            <v-btn color="primary"
                   dark
                   absolute
                   right
                   fab
                   class="save-submission-fab"
                   @click="saveSubmission">
                <v-icon>save</v-icon>
            </v-btn>
        </v-fab-transition>
    </v-container>
</template>

<script>
import templateById from '../../lib/templateById'
import { fieldComponents, fieldDefinitionKeys } from '../../lib/fieldUtils'
import gql from 'graphql-tag'

export default {
  layout: 'dashboard',
  data() {
    return {
      didSetValues: false,
      fieldComponents,
      fieldDefinitionKeys,
      checkboxFields: [],
      textFields: []
    }
  },
  async asyncData({ params }) {
    return {
      id: params.templateId,
      documentTemplate: null
    }
  },
  methods: {
    fieldValue(fieldHeader) {
      if (fieldHeader.type === 'MARKDOWN') return {}
      if (!this.didSetValues) {
        console.log(this.documentTemplate.textFields)
        this.checkboxFields = this.documentTemplate.checkboxFields.map(cf => ({
          value: cf.defaultValue
        }))
        this.textFields = this.documentTemplate.textFields.map(tf => ({
          value: tf.defaultValue
        }))
        this.didSetValues = true
      }

      return fieldHeader.type === 'CHECKBOX'
        ? this.checkboxFields[
            this.documentTemplate.checkboxFields.findIndex(
              cf => cf.id === fieldHeader.definitionId
            )
          ]
        : this.textFields[
            this.documentTemplate.textFields.findIndex(
              cf => cf.id === fieldHeader.definitionId
            )
          ]
    },
    fieldProps(fieldHeader) {
      let definition = this.documentTemplate[
        fieldDefinitionKeys[fieldHeader.type]
      ].find(fd => fd.id === fieldHeader.definitionId)

      return {
        ...definition
      }
    },
    async saveSubmission() {
      let data = {
        template: {
          connect: {
            id: this.id
          }
        },
        state: this.documentTemplate.workflowNodes.find(
          wn => wn.type === 'ENTRY'
        ).id
        //checkboxFields:
      }
      await this.$apollo.mutate({
        mutation: gql`
          mutation createSubmission($data: SubmissionCreateInput!) {
            createSubmission(data: $data) {
              id
            }
          }
        `,
        variables: {
          data
        }
      })
      this.$router.push({
        path: '/templates'
      })
    }
  },
  apollo: {
    documentTemplate: {
      query: templateById,
      variables() {
        return { id: this.id }
      },
      result({ documentTemplate }) {}
    }
  }
}
</script>

<style>
.save-submission-fab {
  bottom: 70px;
}
</style>
