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
                        <svg class="node-preview-svg" :style="{height: nodeType.height + 50}">
                            <component :is="nodeType.component" :node="previewNodeData(nodeType)" :nodeType="nodeType" />

                            <template v-for="(connector, ci) in nodeType.connectors">
                                <circle :key="ci" :cx="previewNodeData(nodeType).x + connector.x" :cy="previewNodeData(nodeType).y + connector.y" :r="5" :fill="connector.color" style="filter:url(#dropshadow)" :class="{'connector-out': connector.type === 'OUT'}" />
                            </template>
                        </svg>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="$emit('nodeAdded', typename)">Add {{nodeType.name.toLowerCase()}}</v-btn>
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
    },
    previewNodeData(nodeType) {
      return { id: 'node_preview', x: 210 - nodeType.width / 2, y: 10 }
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

<style scoped>
.node-preview-svg {
  width: 100%;
}
</style>
