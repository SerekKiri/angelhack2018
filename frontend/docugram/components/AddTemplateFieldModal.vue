<template>
    <v-dialog :value="open" @input="emitDialogEvent" max-width="500">
        <v-card>
            <v-card-title class="headline">Add field</v-card-title>
            <v-card-text>
                <p>Select a field type to be added</p>

                <v-card v-for="fp in fieldPreviews" :key="fp.type + fp.name">
                    <component :is="fp.component" v-bind="fp.props" />
                </v-card>

            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" flat @click.native="$emit('close')">Cancel</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import TextField from './fields/TextField.vue'
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
