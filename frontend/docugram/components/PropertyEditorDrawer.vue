<template>
    <v-navigation-drawer v-if="show" @input="_ => _" :value="show" fixed hide-overlay right class="pe-drawer elevation-7 pa-3" clipped permanent stateless>
        <h3 class="heading">Properties</h3>
        <div v-for="propertyDefinition in model" :key="propertyDefinition.key">
            <v-text-field :label="propertyDefinition.label" v-if="propertyDefinition.type === String" :value="value[propertyDefinition.key]" @input="propertyChanged(propertyDefinition.key, $event)" />
            <v-text-field :label="propertyDefinition.label" v-else-if="propertyDefinition.type === 'LONG_STRING'" multi-line :value="value[propertyDefinition.key]" @input="propertyChanged(propertyDefinition.key, $event)" />
            <v-checkbox :label="propertyDefinition.label" v-else-if="propertyDefinition.type === Boolean" :input-value="value[propertyDefinition.key]" @change="propertyChanged(propertyDefinition.key, $event)" />
            <div v-else>Jesteś debilem</div>
        </div>
    </v-navigation-drawer>
</template>

<script>
export default {
  props: ['model', 'show', 'value'],
  methods: {
    propertyChanged(key, value) {
      console.log({ key, value })
      this.$emit('input', {
        ...this.value,
        [key]: value
      })
    }
  }
}
</script>

<style scoped>
.pe-drawer {
  margin-top: 48px;
}
</style>
