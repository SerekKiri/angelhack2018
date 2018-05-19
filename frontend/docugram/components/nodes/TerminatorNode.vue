<template>
    <g>
        <rect width="100" height="30" :fill="nodeType.color" :x="node.x" :y="node.y" v-on="$listeners" rx="5" ry="5" style="filter:url(#dropshadow)" :stroke="selected ? '#ff3d00' : null" />
        <text width="100" height="30" fill="white" :x="node.x + 50" :y="node.y + 15" text-anchor="middle" alignment-baseline="central" v-on.stop="$listeners" style="user-select: none;">{{nodeType.name}}</text>

    </g>
</template>

<script>
export default {
  props: ['node', 'nodeType', 'selected']
}
</script>

<style>
</style>
