package io.github.feelfree.docugram.utils

import android.content.Context

interface CredentialPreferencesApi {
    var token : String?
    var name : String?
    var email : String?
}

class CredentialPreferences(context : Context) : Preferences(context), CredentialPreferencesApi {
    override var token: String? by stringPref()
    override var email: String? by stringPref()
    override var name: String? by stringPref()
}