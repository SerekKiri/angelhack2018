package io.github.feelfree.docugram.ui.modules.mainactivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.feelfree.docugram.ui.modules.notifications.NotificationFragment
import io.github.feelfree.docugram.ui.modules.notifications.NotificationModule

@Module
abstract class MainActivityFragmentProvider {
    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun provideNotificationsFragment() : NotificationFragment
}