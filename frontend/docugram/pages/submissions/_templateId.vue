<template>
    <div v-if="!!documentTemplate">
        <h1>Create new submissioN: {{documentTemplate.name}}</h1>
        <div v-for="(fieldHeader, fieldIndex) in documentTemplate.fieldHeaders"
             :key="fieldHeader.id"
             class="pa-2">
            <component :is="fieldComponents[fieldHeader.type]"
                       v-bind="fieldProps(fieldHeader)"
                       :editorMode="false" />
        </div>
    </div>
</template>

<script>
import templateById from '../../lib/templateById'
import { fieldComponents, fieldDefinitionKeys } from '../../lib/fieldUtils'

export default {
  data() {
    return {
      fieldComponents,
      fieldDefinitionKeys
    }
  },
  async asyncData({ params }) {
    return {
      id: params.templateId,
      documentTemplate: null
    }
  },
  methods: {
    fieldProps(fieldHeader) {
      let definition = this.documentTemplate[
        fieldDefinitionKeys[fieldHeader.type]
      ].find(fd => fd.id === fieldHeader.definitionId)

      return {
        ...definition
      }
    }
  },
  apollo: {
    documentTemplate: {
      query: templateById,
      variables() {
        return { id: this.id }
      }
    }
  }
}
</script>

<style>
</style>
