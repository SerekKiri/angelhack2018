<template>
    <v-dialog v-model="open">
        <v-card>
            <v-card-title class="headline">Add field</v-card-title>
            <v-card-text>Select a field type to be added</v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" flat @click.native="$emit('close')">Cancel</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
  props: {
    open: {
      required: true,
      type: Boolean
    }
  }
}
</script>

<style>
</style>
