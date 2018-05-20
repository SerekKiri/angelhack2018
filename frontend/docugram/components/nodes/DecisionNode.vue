<template>
  <g>
    <rect width="100" height="100" fill="#f1c40f" v-on="$listeners" style="filter:url(#dropshadow)" :transform="`translate(${node.x + 50}, ${node.y}) rotate(45)`" :stroke="selected ? '#ff3d00' : null" />
    <text width="100" height="100" fill="white" :x="node.x + sq2 + 50" :y="node.y + sq2 * 50" text-anchor="middle" alignment-baseline="central" v-on.stop="$listeners" style="user-select: none;">{{nodeType.name}}</text>
  </g>
</template>

<script>
export default {
  props: ['node', 'selected', 'nodeType'],
  data() {
    return {
      sq2: 1.41421356
    }
  }
}
</script>

<style>
</style>
