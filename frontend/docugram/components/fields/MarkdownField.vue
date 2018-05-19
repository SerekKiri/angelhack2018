<template>
    <div v-on="$listeners" v-html="renderedHtml" />
</template>

<script>
import marked from 'marked'
export default {
  props: ['content'],
  computed: {
    renderedHtml() {
        return marked(this.content)
    }
  }
}
</script>

<style>
</style>
