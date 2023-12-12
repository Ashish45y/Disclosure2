package dev.ashish.disclosure.di.component

import android.content.Context
import dagger.Component
import dev.ashish.disclosure.DisclosureApplication
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: DisclosureApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): Repository

}