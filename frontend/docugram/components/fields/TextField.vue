<template>
    <v-text-field v-on="$listeners" :value="value" :disabled="disabled" :label="label"></v-text-field>
</template>

<script>
export default {
    props: ['value', 'label', 'disabled']
}
</script>

<style>

</style>
