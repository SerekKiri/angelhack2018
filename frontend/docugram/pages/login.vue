<template>
  <div>
    <v-form v-model="valid" ref="form" lazy-validation>
      <div class="text-xs-center">
        <img src="/logo.svg" alt="logo" />
      </div>
      <v-alert v-if="!!error" value="true" type="error">
        {{error}}
      </v-alert>
      <v-text-field color="primary" label="Your email" v-model="email" :rules="emailRules" required @keyup.enter="login"></v-text-field>
      <v-text-field color="primary" label="Password" v-model="password" type="password" :rules="passwordRules" required @keyup.enter="login"></v-text-field>
      <v-btn color="secondary" :disabled="!valid" @click="login">
        Login
      </v-btn>
    </v-form>
  </div>
</template>

<script>
import gql from 'graphql-tag'
import { setCookie } from '../lib/cookieUtils.js'
export default {
  data() {
    return {
      error: null,
      valid: false,
      email: '',
      emailRules: [v => !!v || 'Email is required'],
      password: '',
      passwordRules: [v => !!v || 'Password is required']
    }
  },
  methods: {
    async login() {
      if (this.$refs.form.validate()) {
        try {
          let { data } = await this.$apollo.mutate({
            variables: {
              email: this.email,
              password: this.password
            },
            mutation: gql`
              mutation login($email: String!, $password: String!) {
                login(email: $email, password: $password) {
                  token
                  user {
                    id
                    email
                    name
                  }
                }
              }
            `
          })
          setCookie('token', data.login.token)
          this.$router.push({
            path: '/user'
          })
        } catch (e) {
          this.error = e.message
        }
      }
    }
  }
}
</script>

<style scoped>
</style>
