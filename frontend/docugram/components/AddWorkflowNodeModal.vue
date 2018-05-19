<template>
    <v-dialog :value="open" @input="emitDialogEvent" max-width="500">
        <v-card>
            <v-card-title class="headline">Add node</v-card-title>
            <v-card-text>
                <p>Select a node type to be added</p>

                <v-card v-for="(nodeType, typename) in nodeTypes" :key="typename" class="mb-3">
                    <v-card-title class="heading">{{nodeType.name}}</v-card-title>
                    <v-card-text>
                        <!-- <component :is="nodeType.component" v-bind="fp.props" /> -->
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="$emit('fieldAdded', nodeType.type)">Add {{nodeType.name.toLowerCase()}}</v-btn>
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
import nodeTypes from '../lib/nodeTypes'

export default {
  data() {
    return {
      nodeTypes
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
