package dev.ashish.disclosure.di.component

import dagger.Component
import dev.ashish.disclosure.di.FragmentScope
import dev.ashish.disclosure.di.module.FragmentModule
import dev.ashish.disclosure.ui.topheadline.TopHeadLineFragment

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: TopHeadLineFragment)

}