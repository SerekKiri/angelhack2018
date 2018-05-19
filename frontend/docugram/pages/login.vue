<template>
<div>
  <v-form v-model="valid" ref="form" lazy-validation>
    <v-text-field
      color="primary"
      label="Your email"
      v-model="email"
      :rules="emailRules"
      @keyup.enter="login"
      required
    ></v-text-field>
    <v-text-field
      color="primary"
      label="Password"
      v-model="password"
      type="password"
      :rules="passwordRules"
      @keyup.enter="login"
      required
    ></v-text-field>
    <v-btn
      color="secondary"
      @click="login"
      :disabled="!valid"
      to="/user"
    >
    Login
    </v-btn>
  </v-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      valid: false,
      email: '',
      emailRules: [v => !!v || 'Email is required'],
      password: '',
      passwordRules: [v => !!v || 'Password is required']
    }
  },
  methods: {
    login () {
      if (this.$refs.form.validate()) {
        this.$store
          .dispatch('LOGIN', { username: this.username, password: this.password })
          .then(() => {
            console.log('logged')
            this.$router.push({ name: 'Home' })
          })
      }
    }
  }
}
</script>

<style scoped>
</style>
