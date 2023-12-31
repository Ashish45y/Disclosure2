package dev.ashish.disclosure.di.component

import dagger.Component
import dev.ashish.disclosure.di.ActivityScope
import dev.ashish.disclosure.di.module.ActivityModule
import dev.ashish.disclosure.ui.activity.MainActivity
import dev.ashish.disclosure.ui.topheadline.TopHeadLineFragment

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}