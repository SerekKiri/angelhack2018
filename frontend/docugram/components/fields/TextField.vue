<template>
    <v-text-field v-on="$listeners" :value="value" :disabled="disabled" :label="label" persistent-hint :readonly="editorMode" :class="{'input-group--dirty': editorMode}"></v-text-field>
</template>

<script>
export default {
  props: ['value', 'label', 'disabled', 'editorMode']
}
</script>

<style>
</style>
