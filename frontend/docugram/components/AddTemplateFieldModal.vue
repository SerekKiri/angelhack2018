<template>
    <v-dialog :value="open" @input="emitDialogEvent" max-width="500">
        <v-card>
            <v-card-title class="headline">Add field</v-card-title>
            <v-card-text>
                <p>Select a field type to be added</p>

                <v-card v-for="fp in fieldPreviews" :key="fp.type + fp.name" class="mb-3">
                    <v-card-title class="heading">{{fp.name}}</v-card-title>
                    <v-card-text>
                        <component :is="fp.component" v-bind="fp.props" />
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="$emit('fieldAdded', fp.type)">Add {{fp.name.toLowerCase()}}</v-btn>
                    </v-card-actions>
                </v-card>

            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" flat @click.native="$emit('close')">Cancel</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import TextField from './fields/TextField.vue'
import CheckboxField from './fields/CheckboxField.vue'
import MarkdownField from './fields/MarkdownField.vue'

const fieldPreviews = [
  {
    name: 'Text field',
    type: 'TEXT',
    component: TextField,
    props: {
      label: 'Sample label',
      value: 'Text field',
      disabled: true
    }
  },
  {
    name: 'Checkbox',
    type: 'CHECKBOX',
    component: CheckboxField,
    props: {
      label: 'Sample label',
      value: true,
      disabled: true
    }
  },
  {
    name: 'Description paragraph',
    type: 'MARKDOWN',
    component: MarkdownField,
    props: {
      content: 'Yout **rich** text here!'
    }
  }
]

export default {
  data() {
    return {
      fieldPreviews
    }
  },
  methods: {
    emitDialogEvent(ev) {
      if (ev === false) this.$emit('close')
    }
  },
  props: {
    open: {
      required: true,
      type: Boolean
    }
  }
}
</script>

<style>
</style>
