<template>
    <v-container>
        <v-card>
            <v-list v-if="!!documentTemplates">
                <v-list-tile v-for="template in documentTemplates"
                             :key="template.id"
                             nuxt
                             :to="'/submissions/'+template.id">
                    <v-list-tile-content>
                        <v-list-tile-title>{{template.name}}</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
            </v-list>
        </v-card>
    </v-container>
</template>

<script>
import gql from 'graphql-tag'

export default {
  layout: 'dashboard',
  apollo: {
    documentTemplates: gql`
      query {
        documentTemplates {
          id
          name
        }
      }
    `
  }
}
</script>

<style>
</style>
