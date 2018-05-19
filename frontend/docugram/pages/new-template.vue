<template>
  <div>
    <h1 class="subheader">Create a document template</h1>
    <v-tabs v-model="activeTab">
      <v-tab :key="0" ripple>
        Template
      </v-tab>
      <v-tab :key="2" ripple>
        Workflow
      </v-tab>
      <v-tab-item :key="0">
        <AddTemplateFieldModal :open="addFieldModalOpen" @close="addFieldModalOpen = false" @fieldAdded="addField" />
        <v-card flat>
          <v-card-text>
            <component v-for="fieldHeader in template.fieldHeaders" :is="fieldComponents[fieldHeader.type]" :key="fieldHeader.id" v-bind="fieldProps(fieldHeader)" />
            <v-btn color="primary" block @click="addFieldModalOpen = true">
              <v-icon>
                add
              </v-icon>
            </v-btn>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item :key="1">
        <v-card flat>
          <v-card-text>:)</v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import AddTemplateFieldModal from '../components/AddTemplateFieldModal'
import cuid from 'cuid'
import TextField from '../components/fields/TextField.vue'
import CheckboxField from '../components/fields/CheckboxField.vue'
import MarkdownField from '../components/fields/MarkdownField.vue'

const fieldComponents = {
  TEXT: TextField,
  CHECKBOX: CheckboxField,
  MARKDOWN: MarkdownField
}

/*const fieldMockProps = {
    TEXT: {
        value: 
    }
}*/

const fieldDefinitionKeys = {
  TEXT: 'textFields',
  CHECKBOX: 'checkboxFields',
  MARKDOWN: 'markdownFields'
}

const fieldProperties = {
  TEXT: [
    {
      type: String,
      key: 'label',
      label: 'Label'
    },
    {
      type: String,
      key: 'defaultValue',
      label: 'Default value'
    }
  ]
}

export default {
  data() {
    return {
      activeTab: 0,
      addFieldModalOpen: false,
      fieldComponents,
      fieldDefinitionKeys,
      fieldProperties,
      template: {
        fieldHeaders: [],
        textFields: [],
        checkboxFields: [],
        markdownFields: []
      }
    }
  },
  methods: {
    fieldProps(fieldHeader) {
      return {
        ...this.template[fieldDefinitionKeys[fieldHeader.type]].find(
          fd => fd.id === fieldHeader.definitionId
        )
      }
    },
    addField(type) {
      this.addFieldModalOpen = false
      let fieldDefinitionId = cuid()
      this.template.fieldHeaders.push({
        id: cuid(),
        type,
        order: this.template.fieldHeaders.length,
        definitionId: fieldDefinitionId
      })
      switch (type) {
        case 'TEXT':
          this.template.textFields.push({
            id: fieldDefinitionId,
            label: 'New field',
            defaultValue: ''
          })
          break
        case 'CHECKBOX':
          this.template.checkboxFields.push({
            id: fieldDefinitionId,
            label: 'New field',
            defaultValue: false
          })
          break
        case 'MARKDOWN':
          this.template.markdownFields.push({
            id: fieldDefinitionId,
            content: 'Your custom **description**.'
          })
          break
      }
    }
  },
  layout: 'dashboard',
  components: {
    AddTemplateFieldModal
  }
}
</script>

<style>
</style>
