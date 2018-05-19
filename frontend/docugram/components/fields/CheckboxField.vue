<template>
    <v-checkbox v-on="$listeners" :value="value" :disabled="disabled" :label="label" />
</template>

<script>
export default {
  props: ['value', 'label', 'disabled']
}
</script>

<style>
</style>
