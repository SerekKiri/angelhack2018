<template>
    <div>
        <h1 class="subheader">Create a document template</h1>
        <v-tabs v-model="active" color="primary" dark slider-color="secondary">
            <v-tab :key="0" ripple>
                Template
            </v-tab>
            <v-tab :key="2" ripple>
                Workflow
            </v-tab>
            <v-tab-item v-for="n in 2" :key="n">
                <v-card flat>
                    <v-card-text> dupa</v-card-text>
                </v-card>
            </v-tab-item>
        </v-tabs>
    </div>
</template>

<script>
export default {
  layout: 'dashboard'
}
</script>

<style>
</style>
