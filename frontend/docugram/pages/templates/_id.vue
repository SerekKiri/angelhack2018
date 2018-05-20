<template>
    <div v-if="documentTemplate">
        <h1>{{documentTemplate.name}}</h1>
    </div>
</template>

<script>
import gql from 'graphql-tag'
export default {
  async asyncData({ params }) {
    return {
      id: params.id,
      documentTemplate: null
    }
  },
  apollo: {
    documentTemplate: {
      query: gql`
        query dtById($id: ID!) {
          documentTemplate(where: { id: $id }) {
            id
            name
            fieldHeaders {
              id
              type
              order
              definitionId
            }
            checkboxFields {
              id
              label
              defaultValue
            }
            textFields {
              id
              label
              defaultValue
            }
            markdownFields {
              id
              content
            }
            workflowNodes {
              id
              x
              y
              type
              properties
              connections {
                id
                targetNodeId
                targetConnector
                sourceConnector
              }
            }
          }
        }
      `,
      variables() {
        return { id: this.id }
      }
    }
  }
}
</script>

<style>
</style>
