import gql from 'graphql-tag'

export default gql`
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
`
