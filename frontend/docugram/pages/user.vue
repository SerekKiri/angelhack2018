<template>
  <v-layout column justify-center align-center>
    <v-flex xs12 sm8 md6>
      <v-card class="mt-3">
        <v-card-title class="headline">Welcome {{me && me.name}}!</v-card-title>
        <v-card-text>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>
<script>
import gql from "graphql-tag";
export default {
  apollo: {
    me: {
      query: gql`
        query {
          me {
            id
            name
          }
        }
      `
    }
  },
  layout: 'dashboard'
}
</script>
