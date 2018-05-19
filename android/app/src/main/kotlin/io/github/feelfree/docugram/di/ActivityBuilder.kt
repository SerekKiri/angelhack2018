package io.github.feelfree.docugram.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.feelfree.docugram.ui.modules.template.TemplateActivity
import io.github.feelfree.docugram.ui.modules.template.TemplateModule

// Every activity that extends BaseActivity should be bind there
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [TemplateModule::class])
    abstract fun bindTemplateActivity() : TemplateActivity
}
