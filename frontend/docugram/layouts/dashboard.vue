<template>
    <v-app dark>
        <v-toolbar fixed app :clipped-left="clipped">
            <v-spacer></v-spacer>
            <v-btn flat to='/'>{{me && me.name}}</v-btn>
            <v-btn flat to='/'>Logout</v-btn>
        </v-toolbar>
        <v-navigation-drawer app clipped permanent>
            <v-list>
                <v-subheader>Document templates</v-subheader>
                <v-divider></v-divider>
                <v-list-tile>
                    <v-list-tile-title>New template</v-list-tile-title>
                    <v-list-tile-action>
                        <v-btn fab small flat>
                            <v-icon>add</v-icon>
                        </v-btn>
                    </v-list-tile-action>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-content>
            <v-container>
                <nuxt />
            </v-container>
        </v-content>
        <v-footer :fixed="fixed" app>
            <span>Docugram &copy; 2017</span>
        </v-footer>
    </v-app>
</template>

<script>
import gql from 'graphql-tag'
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
  data() {
    return {
      clipped: false,
      drawer: true,
      fixed: false,
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'Docugram'
    }
  }
}
</script>
