<template>
    <g>
        <rect
            width="100"
            height="30"
            fill="#9b59b6"
            :x="node.x"
            :y="node.y"
            v-on="$listeners"
            style="filter:url(#dropshadow)"
        />
        <text
            width="100"
            height="30"
            fill="white"
            :x="node.x + 50"
            :y="node.y + 15"
            text-anchor="middle"
            alignment-baseline="central"
            v-on.stop="$listeners"
            style="user-select: none;"
        >Action</text>
    </g>
</template>

<script>
export default {
  props: ["node"]
};
</script>

<style>

</style>
