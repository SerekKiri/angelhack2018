<template>
    <v-app>
        <v-toolbar>
            <v-btn icon
                   @click.stop="rightDrawer = !rightDrawer">
                <v-icon>menu</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn flat
                   to="/user">{{me && me.name}}</v-btn>
            <v-btn flat
                   to='/'>Logout</v-btn>
        </v-toolbar>
        <v-navigation-drawer temporary
                             :left="left"
                             v-model="rightDrawer"
                             fixed>
            <v-list>
                <v-subheader>
                    <v-btn icon
                           @click.stop="rightDrawer = !rightDrawer">
                        <v-icon>arrow_left</v-icon>
                    </v-btn>
                    Document templates
                </v-subheader>
                <v-divider></v-divider>
                <v-list-tile nuxt
                             to="/new-template"
                             color="primary">
                    <v-list-tile-title>New template</v-list-tile-title>
                    <v-list-tile-action>
                        <v-btn fab
                               small
                               flat
                               color="primary">
                            <v-icon>add</v-icon>
                        </v-btn>
                    </v-list-tile-action>
                </v-list-tile>
                 <v-list-tile nuxt
                             to="/templates">
                    <v-list-tile-title>All templates</v-list-tile-title>
                    <v-list-tile-action>
                        <v-btn fab
                               small
                               flat
                               color="primary">
                            <v-icon>list</v-icon>
                        </v-btn>
                    </v-list-tile-action>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-content>
            <!--<v-container> -->
            <nuxt />
            <!-- </v-container> -->
        </v-content>
        <v-footer :fixed="fixed"
                  app>
            <v-card-text>
                <p class="text-md-center footer-text">Docugram &copy; 2018</p>
            </v-card-text>
        </v-footer>
    </v-app>
</template>
<style scoped>
.footer-text {
  user-select: none;
}
</style>
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
      left: true,
      rightDrawer: false,
      title: 'Docugram'
    }
  }
}
</script>
