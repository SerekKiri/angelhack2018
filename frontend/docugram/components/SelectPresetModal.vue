<template>
    <v-dialog :value="open"
              @input="emitDialogEvent">
        <v-card>
            <v-card-title class="headline">Select a premade template</v-card-title>
            <v-card-text>
                <v-layout row
                          wrap>
                    <v-flex xs4
                            class="pa-3">
                        <v-card>
                            <v-card-title primary-title>
                                <div>
                                    <div class="headline">School trip consent</div>
                                    <span class="grey--text">School</span>
                                </div>
                            </v-card-title>
                            <v-card-actions>
                                <v-btn flat
                                       @click="usePreset('trip-consent')">Use preset</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                    <v-flex xs4
                            class="pa-3">
                        <v-card>
                            <v-card-title primary-title>
                                <div>
                                    <div class="headline">School enrollment process</div>
                                    <span class="grey--text">School</span>
                                </div>
                            </v-card-title>
                            <v-card-actions>
                                <v-btn flat
                                       @click="usePreset('school-enrollment')">Use preset</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                    <v-flex xs4
                            class="pa-3">
                        <v-card>
                            <v-card-title primary-title>
                                <div>
                                    <div class="headline">Job recruitment</div>
                                    <span class="grey--text">Company</span>
                                </div>
                            </v-card-title>
                            <v-card-actions>
                                <v-btn flat
                                       @click="usePreset('job-recruitment')">Use preset</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary"
                       flat
                       @click.native="emitDialogEvent(false)">Cancel</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
const presets = {
  'trip-consent': {
    fieldHeaders: [
      {
        type: 'TEXT',
        order: 0,
        definitionId: 'fname'
      },
      {
        type: 'TEXT',
        order: 1,
        definitionId: 'lname'
      },
      {
        type: 'CHECKBOX',
        order: 2,
        definitionId: 'medi_rq'
      },
      {
        type: 'MARKDOWN',
        order: 3,
        definitionId: 'warning'
      }
    ],
    textFields: [
      {
        id: 'fname',
        label: "Student's first name",
        defaultValue: ''
      },
      {
        id: 'lname',
        label: "Student's last name",
        defaultValue: ''
      }
    ],
    checkboxFields: [
      {
        id: 'medi_rq',
        label: 'Special medication required',
        defaultValue: false
      }
    ],
    markdownFields: [
      {
        id: 'warning',
        content: `Please note that the trip will be cancelled if there will be less than 90% attendance.`
      }
    ]
  }
}
export default {
  data() {
    return {
      open: true
    }
  },
  methods: {
    emitDialogEvent(ev) {
      if (ev === false) this.$router.push({ path: '/user' })
    },
    usePreset(name) {
      this.$emit('usePreset', presets[name])
      this.open = false
    }
  }
}
</script>

<style>
</style>
