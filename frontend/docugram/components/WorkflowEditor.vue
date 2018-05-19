<template>
    <v-card>
        <AddWorkflowNodeModal :open="!!addWorkflowNodeModal" @close="addWorkflowNodeModal = false" @nodeAdded="nodeAdded" />
        <div v-if="selectedNodeIndex !== null">
            <PropertyEditorDrawer :show="selectedNodeIndex !== null" :model="nodeTypes[nodes[selectedNodeIndex].type].propertiesModel" :value="{}" />
        </div>
        <svg :style="rootStyles" @mouseover="diagramHovered" @mouseout="diagramMouseOut" @mousemove="diagramMouseMove" @mouseup="endDrag()" class="main-diagram" ref="svg">
            <defs>
                <!-- <pattern id="smallGrid" width="8" height="8" patternUnits="userSpaceOnUse">
                    <path d="M 8 0 L 0 0 0 8" fill="none" stroke="gray" stroke-width="0.5" />
                </pattern> -->
                <pattern id="grid" width="80" height="80" patternUnits="userSpaceOnUse">
                    <rect width="80" height="80" fill="url(#smallGrid)" />
                    <path d="M 80 0 L 0 0 0 80" fill="none" stroke="gray" stroke-width="1" />
                </pattern>
            </defs>

            <rect width="100%" height="100%" fill="url(#grid)" />
            <filter id="dropshadow" height="250%" width="250%">
                <feGaussianBlur in="SourceAlpha" stdDeviation="3" />
                <!-- stdDeviation is how much to blur -->
                <feOffset dx="2" dy="2" result="offsetblur" />
                <!-- how much to offset -->
                <feComponentTransfer>
                    <feFuncA type="linear" slope="0.2" />
                    <!-- slope is the opacity of the shadow -->
                </feComponentTransfer>
                <feMerge>
                    <feMergeNode/>
                    <!-- this contains the offset blurred image -->
                    <feMergeNode in="SourceGraphic" />
                    <!-- this contains the element that the filter is applied to -->
                </feMerge>
            </filter>
             <filter id="deepShadow" height="250%" width="250%">
                <feGaussianBlur in="SourceAlpha" stdDeviation="3" />
                <!-- stdDeviation is how much to blur -->
                <feOffset dx="8" dy="8" result="offsetblur" />
                <!-- how much to offset -->
                <feComponentTransfer>
                    <feFuncA type="linear" slope="0.2" />
                    <!-- slope is the opacity of the shadow -->
                </feComponentTransfer>
                <feMerge>
                    <feMergeNode/>
                    <!-- this contains the offset blurred image -->
                    <feMergeNode in="SourceGraphic" />
                    <!-- this contains the element that the filter is applied to -->
                </feMerge>
            </filter>

            <g v-for="(node, i) in nodes" :key="i">
                <component :is="nodeTypes[node.type].component" :node="node" :nodeType="nodeTypes[node.type]" @mouseover="nodeHovered(node)" @mouseout="nodeEndHover(node)" @mousedown="nodeDrag(node, $event)" @click.prevent="selectedNodeIndex = i" :selected="selectedNodeIndex === i" />
                <line v-for="(connection, ci) in node.connections" :key="ci" :x1="node.x + connection.sourceConnector.x" :y1="node.y + connection.sourceConnector.y" :x2="connection.targetNode.x + connection.targetConnector.x" :y2="connection.targetNode.y + connection.targetConnector.y" stroke-width="2" stroke="black" />
                <template v-for="(connector, ci) in nodeTypes[node.type].connectors">
                    <circle :key="ci" :cx="node.x + connector.x" :cy="node.y + connector.y" :r="connectorSize(connector)" :fill="connector.color" style="filter:url(#dropshadow)" :class="{'connector-out': connector.type === 'OUT'}" @mousedown="connectorDrag(node, connector, $event)" @mouseup="connectorEndDrag(node, connector, $event)" />
                    <circle v-if="connector.type === 'IN' && cursorState === 'CONNECTOR_DRAG'" :key="ci + 'qqq'" :cx="node.x + connector.x" :cy="node.y + connector.y" :r="10" fill="transparent" stroke-width="2" :stroke="connector.color" style="filter:url(#dropshadow)" :class="{'connector-out': connector.type === 'OUT'}" @mousedown="connectorDrag(node, connector, $event)" @mouseup="connectorEndDrag(node, connector, $event)">
                        <animate attributeType="XML" attributeName="r" from="0" to="20" dur="1s" repeatCount="indefinite" />
                        <animate attributeType="CSS" attributeName="opacity" from="1" to="0" dur="1s" repeatCount="indefinite" />
                    </circle>
                </template>
            </g>
            <line v-if="temporaryConnection" :x1="temporaryConnection.x1" :y1="temporaryConnection.y1" :x2="temporaryConnection.x2" :y2="temporaryConnection.y2" stroke-width="2" stroke="black" />
        </svg>
        <!-- <button @click="add('entry')">Add entry</button>
        <button @click="add('action')">Add action</button>
        <button @click="add('decision')">Add decision</button> -->
        <v-fab-transition>
            <v-btn color="primary" dark absolute right fab class="add-node-fab" @click="addWorkflowNodeModal = true">
                <v-icon>add</v-icon>
            </v-btn>
        </v-fab-transition>
    </v-card>
</template>

<script>
import nodeTypes from '../lib/nodeTypes'
import AddWorkflowNodeModal from './AddWorkflowNodeModal'
import PropertyEditorDrawer from './PropertyEditorDrawer'

const randId = _ =>
  Math.random()
    .toString(36)
    .substring(7)

export default {
  name: 'WorkflowEditor',
  data() {
    return {
      cursorState: 'IDLE',
      draggedNode: null,
      dragOffsetX: null,
      dragOffsetY: null,
      nodeTypes,
      temporaryConnection: null,
      connectorDragSourceNode: null,
      connectorDragSourceConnector: null,
      addWorkflowNodeModal: false,
      selectedNodeIndex: null,
      nodes: [
        {
          id: 'node_1',
          type: 'ENTRY',
          x: 20,
          y: 20,
          connections: []
        },
        {
          id: 'node_2',
          type: 'NOTIFICATION',
          x: 100,
          y: 100,
          connections: []
        },
        {
          id: 'node_3',
          type: 'decision',
          x: 300,
          y: 100,
          connections: []
        }
      ]
    }
  },
  components: {
    AddWorkflowNodeModal,
    PropertyEditorDrawer
  },
  methods: {
    nodeHovered() {
      if (this.cursorState === 'IDLE') {
        this.cursorState = 'NODE_HOVER'
      }
    },
    nodeEndHover() {
      if (this.cursorState === 'NODE_HOVER') {
        this.cursorState = 'IDLE'
      }
    },
    diagramHovered() {},
    diagramMouseOut() {
      //  this.cursorState = "IDLE";
      // this.draggedNode = null;
    },
    nodeDrag(node, e) {
      this.cursorState = 'NODE_DRAG'
      this.draggedNode = node
      let rect = this.$refs.svg.getBoundingClientRect()
      this.dragOffsetX = e.clientX - rect.left - node.x
      this.dragOffsetY = e.clientY - rect.top - node.y
    },
    endDrag() {
      this.draggedNode = null
      this.cursorState = 'IDLE'
      this.temporaryConnection = null
    },
    diagramMouseMove(e) {
      let rect = this.$refs.svg.getBoundingClientRect()
      let x = e.clientX - rect.left
      let y = e.clientY - rect.top

      if (this.cursorState === 'NODE_DRAG') {
        this.draggedNode.x = x - this.dragOffsetX
        this.draggedNode.y = y - this.dragOffsetY
      } else if (this.cursorState == 'CONNECTOR_DRAG') {
        this.temporaryConnection.x2 = x
        this.temporaryConnection.y2 = y
      }
    },
    connectorDrag(node, connector, ev) {
      if (connector.type !== 'OUT') return
      console.log('Connector drag')
      this.cursorState = 'CONNECTOR_DRAG'
      this.temporaryConnection = {
        x1: node.x + connector.x,
        y1: node.y + connector.y,
        x2: node.x + connector.x,
        y2: node.y + connector.y
      }
      this.connectorDragSourceNode = node
      this.connectorDragSourceConnector = connector
    },
    connectorEndDrag(node, connector, ev) {
      if (
        this.cursorState !== 'CONNECTOR_DRAG' ||
        node.id === this.connectorDragSourceNode.id ||
        connector.type === 'OUT'
      )
        return
      this.connectorDragSourceNode.connections.push({
        targetNode: node,
        sourceConnector: this.connectorDragSourceConnector,
        targetConnector: connector
      })
      this.cursorState = 'IDLE'
    },
    connectorSize(connector) {
      if (this.cursorState === 'CONNECTOR_DRAG') {
        return connector.type === 'IN' ? 5 : 3
      }
      return connector.type === 'OUT' ? 5 : 3
    },
    getNodeInputConnector(node) {
      return nodeTypes[node.type].connectors[
        Object.keys(nodeTypes[node.type].connectors).find(
          k => nodeTypes[node.type].connectors[k].type === 'IN'
        )
      ]
    },
    nodeAdded(type) {
      this.addWorkflowNodeModal = false
      this.nodes.push({
        id: randId(),
        type: type,
        x: 400,
        y: 400,
        connections: []
      })
    }
  },
  computed: {
    rootStyles() {
      const cursors = {
        NODE_HOVER: 'pointer',
        NODE_DRAG: 'move',
        IDLE: 'default',
        CONNECTOR_DRAG: 'crosshair'
      }
      return {
        cursor: cursors[this.cursorState]
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.main-diagram {
  /* border: 1px solid gray; */
  width: 100%;
  height: calc(100vh - 230px);
}
.connector-out {
  cursor: crosshair;
}
.add-node-fab {
  bottom: 30px;
}
</style>
