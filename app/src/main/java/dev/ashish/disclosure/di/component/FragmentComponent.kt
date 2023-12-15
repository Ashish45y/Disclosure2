package dev.ashish.disclosure.di.component

import android.content.Context
import dagger.Component
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.FragmentScope
import dev.ashish.disclosure.di.module.FragmentModule
import dev.ashish.disclosure.ui.topheadline.TopHeadLineFragment
@FragmentScope
@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(topHeadLineFragment: TopHeadLineFragment)
    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): Repository
}