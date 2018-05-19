<template>
  <v-alert v-if="!!error" value="true" type="error">
    {{error}}
  </v-alert>
  <div v-else>
    <!-- <h1 class="subheader">Create a document template</h1> -->
    <PropertyEditorDrawer :model="selectedField && fieldProperties[selectedField.type]" :show="selectedFieldIndex !== null" :value="selectedFieldDefinition" @hide="selectedFieldIndex = null" @input="updateSelectedDefinition($event)" />
    <v-tabs v-model="activeTab">
      <v-tab :key="0" ripple>
        Template
      </v-tab>
      <v-tab :key="2" ripple>
        Workflow
      </v-tab>
      <v-spacer />
      <v-btn right bottom class="mt-4" color="secondary" @click="save">Save</v-btn>
      <v-tab-item :key="0">
        <AddTemplateFieldModal :open="addFieldModalOpen" @close="addFieldModalOpen = false" @fieldAdded="addField" />

        <v-card flat>
          <v-card-text>
            <div class="force-height">
              <div v-for="(fieldHeader, fieldIndex) in template.fieldHeaders" :key="fieldHeader.id" :class="{'elevation-3': selectedFieldIndex === fieldIndex}" class="pa-2">
                <component :is="fieldComponents[fieldHeader.type]" v-bind="fieldProps(fieldHeader)" @click.prevent="selectField(fieldIndex)" :editorMode="true" />
                <v-btn color="error">Delete</v-btn>
              </div>
            </div>
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
          <v-card-text>
            <WorkflowEditor />
          </v-card-text>
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
import PropertyEditorDrawer from '../components/PropertyEditorDrawer'
import WorkflowEditor from '../components/WorkflowEditor'

import gql from 'graphql-tag'

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
  ],
  CHECKBOX: [
    {
      type: String,
      key: 'label',
      label: 'Label'
    },
    {
      type: Boolean,
      key: 'defaultValue',
      label: 'Default value'
    }
  ],
  MARKDOWN: [
    {
      type: 'LONG_STRING',
      key: 'content',
      label: 'Content'
    }
  ]
}

export default {
  data() {
    return {
      error: null,
      activeTab: 0,
      addFieldModalOpen: false,
      selectedFieldIndex: null,
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
  computed: {
    selectedField() {
      return this.template.fieldHeaders[this.selectedFieldIndex]
    },
    selectedFieldDefinition() {
      if (!this.selectedField) return null
      return {
        ...this.template[fieldDefinitionKeys[this.selectedField.type]].find(
          fd => fd.id === this.selectedField.definitionId
        )
      }
    }
  },
  methods: {
    async save() {
      try {
        for (let fieldHeader of this.template.fieldHeaders) {
          switch (fieldHeader.type) {
            case 'TEXT': {
              let textFieldDefinition = this.template.textFields.find(
                tf => tf.id === fieldHeader.definitionId
              )
              if (!textFieldDefinition) {
                throw new Error(
                  'Jestes debilem, textFieldDefinition jest falsy'
                )
              }
              let {
                data: { createTextField: { id } }
              } = await this.$apollo.mutate({
                mutation: gql`
                  mutation createTextField($data: TextFieldCreateInput!) {
                    createTextField(data: $data) {
                      id
                    }
                  }
                `,
                variables: { data: { ...textFieldDefinition, id: undefined } }
              })
              textFieldDefinition.id = id
              fieldHeader.definitionId = id
              break
            }
            case 'CHECKBOX': {
              let checkboxFieldDefinition = this.template.checkboxFields.find(
                tf => tf.id === fieldHeader.definitionId
              )
              if (!checkboxFieldDefinition) {
                throw new Error(
                  'Jestes debilem, checkboxFieldDefinition jest falsy'
                )
              }
              let {
                data: { createCheckboxField: { id } }
              } = await this.$apollo.mutate({
                mutation: gql`
                  mutation createCheckboxField(
                    $data: CheckboxFieldCreateInput!
                  ) {
                    createCheckboxField(data: $data) {
                      id
                    }
                  }
                `,
                variables: {
                  data: { ...checkboxFieldDefinition, id: undefined }
                }
              })
              checkboxFieldDefinition.id = id
              fieldHeader.definitionId = id
              break
            }
            case 'MARKDOWN': {
              let markdownFieldDefinition = this.template.markdownFields.find(
                tf => tf.id === fieldHeader.definitionId
              )
              if (!markdownFieldDefinition) {
                throw new Error(
                  'Jestes debilem, markdownFieldDefinition jest falsy'
                )
              }
              let {
                data: { createMarkdownField: { id } }
              } = await this.$apollo.mutate({
                mutation: gql`
                  mutation createMarkdownField(
                    $data: MarkdownFieldCreateInput!
                  ) {
                    createMarkdownField(data: $data) {
                      id
                    }
                  }
                `,
                variables: {
                  data: { ...markdownFieldDefinition, id: undefined }
                }
              })
              markdownFieldDefinition.id = id
              fieldHeader.definitionId = id
              break
            }
          }
        }

        // now really create the template
        let templateCreateData = {
          fieldHeaders: {
            create: this.template.fieldHeaders.map(fh => ({
              ...fh,
              id: undefined
            }))
          },
          textFields: {
            connect: this.template.textFields.map(tf => ({
              fdhfdhufd: console.log(tf),
              id: tf.id
            }))
          },
          checkboxFields: {
            connect: this.template.checkboxFields.map(tf => ({
              id: tf.id
            }))
          },
          markdownFields: {
            connect: this.template.markdownFields.map(tf => ({
              id: tf.id
            }))
          }
        }

        await this.$apollo.mutate({
          mutation: gql`
            mutation createDocumentTemplate(
              $data: DocumentTemplateCreateInput!
            ) {
              createDocumentTemplate(data: $data) {
                id
              }
            }
          `,
          variables: { data: templateCreateData }
        })
      } catch (e) {
        console.log(e)
        this.error = e.message
      }
    },
    updateSelectedDefinition(newDefinition) {
      if (this.selectedField === null) return null

      let fieldsOfTypeList = this.template[
        fieldDefinitionKeys[this.selectedField.type]
      ]
      this.template[
        fieldDefinitionKeys[this.selectedField.type]
      ] = fieldsOfTypeList.map(
        fd => (fd.id === this.selectedField.definitionId ? newDefinition : fd)
      )
    },
    fieldProps(fieldHeader) {
      let value = undefined
      let definition = this.template[
        fieldDefinitionKeys[fieldHeader.type]
      ].find(fd => fd.id === fieldHeader.definitionId)

      if (['CHECKBOX', 'TEXT'].indexOf(fieldHeader.type) !== -1) {
        // set value to default value for preview
        value = definition.defaultValue
      }
      return {
        ...definition,
        value
      }
    },
    selectField(fieldIndex) {
      this.selectedFieldIndex = fieldIndex
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
    AddTemplateFieldModal,
    PropertyEditorDrawer,
    WorkflowEditor
  }
}
</script>

<style scoped>
.force-height {
  min-height: 70vh;
}
</style>
