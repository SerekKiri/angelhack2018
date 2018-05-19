<template>
    <div>
        <h1 class="subheader">Create a document template</h1>
        <v-tabs v-model="activeTab">
            <v-tab :key="0" ripple>
                Template
            </v-tab>
            <v-tab :key="2" ripple>
                Workflow
            </v-tab>
            <v-tab-item :key="0">
                <AddTemplateFieldModal :open="addFieldModalOpen" @close="addFieldModalOpen = false" />
                <v-card flat>
                    <v-card-text>
                        <v-btn color="primary" block @click="addFieldModalOpen = true">
                            <v-icon>
                                add
                            </v-icon>
                        </v-btn>
                    </v-card-text>
                </v-card>
            </v-tab-item>
            <v-tab-item :key="1">
                <v-card flat>
                    <v-card-text> dupa</v-card-text>
                </v-card>
            </v-tab-item>
        </v-tabs>
    </div>
</template>

<script>
import AddTemplateFieldModal from '../components/AddTemplateFieldModal'

export default {
  data() {
    return { activeTab: 0, addFieldModalOpen: false }
  },
  layout: 'dashboard',
  components: {
    AddTemplateFieldModal
  }
}
</script>

<style>
</style>
