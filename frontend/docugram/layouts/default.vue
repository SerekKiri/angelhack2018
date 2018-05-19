<template>
  <v-app dark>
    <v-toolbar fixed app :clipped-left="clipped">
      <v-btn to="/">
        <v-icon>home</v-icon>HOME
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn flat to='/user'>Login</v-btn>
    </v-toolbar>
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
  export default {
    data () {
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
