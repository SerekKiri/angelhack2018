import TextField from '../components/fields/TextField.vue'
import CheckboxField from '../components/fields/CheckboxField.vue'
import MarkdownField from '../components/fields/MarkdownField.vue'

export const fieldComponents = {
  TEXT: TextField,
  CHECKBOX: CheckboxField,
  MARKDOWN: MarkdownField
}

export const fieldDefinitionKeys = {
  TEXT: 'textFields',
  CHECKBOX: 'checkboxFields',
  MARKDOWN: 'markdownFields'
}
