<template>
    <v-container>
        <v-card>
            <v-list>
                <v-list-tile>
                    <v-list-tile-content>
                        <v-list-tile-title>Profile photo</v-list-tile-title>
                        <v-list-tile-sub-title>Change your Google+ profile photo</v-list-tile-sub-title>
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
    templates: gql`
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
