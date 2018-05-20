<template>
    <div v-if="documentTemplate">
        <h1>{{documentTemplate.name}}</h1>
    </div>
</template>

<script>
import templateById from "../../lib/templateById"
export default {
  async asyncData({ params }) {
    return {
      id: params.id,
      documentTemplate: null
    }
  },
  apollo: {
    documentTemplate: {
      query: templateById,
      variables() {
        return { id: this.id }
      }
    }
  }
}
</script>

<style>
</style>
