package dev.ashish.disclosure.di.component

import android.content.Context
import dagger.Component
import dev.ashish.disclosure.DisclosureApplication
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.module.ApplicationModule
import dev.ashish.disclosure.ui.country.CountryAdapter
import dev.ashish.disclosure.ui.language.LanguageAdapter
import dev.ashish.disclosure.ui.newssource.NewsSourceAdaptor
import dev.ashish.disclosure.ui.search.SearchAdapter
import dev.ashish.disclosure.ui.topheadline.TopHeadlineAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: DisclosureApplication)
    @ApplicationContext
    fun getContext(): Context
    fun getNetworkService() : NetworkService
    fun getTopHeadLineAdapter() : TopHeadlineAdapter
    fun getRepository() : Repository
    fun getNewsSourceAdapter() : NewsSourceAdaptor
    fun getSearchAdapter() : SearchAdapter
    fun getLanguageAdapter(): LanguageAdapter
    fun getCountryAdapter(): CountryAdapter

}