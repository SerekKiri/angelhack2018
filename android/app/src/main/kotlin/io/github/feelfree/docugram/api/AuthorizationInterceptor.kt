package io.github.feelfree.docugram.api

import io.github.feelfree.docugram.utils.CredentialPreferencesApi
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(val credentialPreferencesApi: CredentialPreferencesApi) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val req = chain!!.request()
        if (!credentialPreferencesApi.token.isNullOrEmpty()) {
            val newrreq = req.newBuilder()
                    .addHeader("Authorization", "Bearer " + credentialPreferencesApi.token!!)
                    .build()
            return chain.proceed(newrreq)
        } else {
            return chain.proceed(req)
        }
    }
}