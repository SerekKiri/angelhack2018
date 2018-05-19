package io.github.feelfree.docugram.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.feelfree.docugram.DocugramApp
import io.github.feelfree.docugram.di.modules.NetworkModule
import io.github.feelfree.docugram.di.modules.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ActivityBuilder::class] )
internal interface AppComponent : AndroidInjector<DocugramApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DocugramApp>()
}