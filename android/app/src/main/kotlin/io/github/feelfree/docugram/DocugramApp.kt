package io.github.feelfree.docugram

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.feelfree.docugram.di.DaggerAppComponent

class DocugramApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    companion object {
        val BASE_URL = "http://10.1.1.222:4466/"
    }
}