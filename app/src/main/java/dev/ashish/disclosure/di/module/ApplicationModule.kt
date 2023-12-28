package dev.ashish.disclosure.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.ashish.disclosure.DisclosureApplication
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.data.model.SearchResponse
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.BaseUrl
import dev.ashish.disclosure.di.CountryListQualifier
import dev.ashish.disclosure.di.LanguageListQualifier
import dev.ashish.disclosure.di.NewsSourceListQualifier
import dev.ashish.disclosure.ui.country.CountryAdapter
import dev.ashish.disclosure.ui.language.LanguageAdapter
import dev.ashish.disclosure.ui.newssource.NewsSourceAdaptor
import dev.ashish.disclosure.ui.search.SearchAdapter
import dev.ashish.disclosure.ui.topheadline.TopHeadlineAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: DisclosureApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }
    @Provides
    @Singleton
    fun providesArticleList(): List<Article> {
        return emptyList()
    }
    @Provides
    @Singleton
    fun providesTopHeadlineAdapter(articleList: List<Article>): TopHeadlineAdapter {
        return TopHeadlineAdapter(articleList.toMutableList())
    }
    @Provides
    @Singleton
    @CountryListQualifier
    fun providesCountryList(): List<NewsSources>{
        return emptyList()
    }
    @Provides
    @Singleton
    fun providesCountryListAdapter(
        @CountryListQualifier countryList : List<NewsSources>
    ): CountryAdapter{
        return CountryAdapter(countryList.toMutableList())
    }


    @Provides
    @Singleton
    fun providesLanguageAdapter(
        @LanguageListQualifier languageList: List<NewsSources>): LanguageAdapter{
        return LanguageAdapter(languageList.toMutableList())
    }
    @Provides
    @Singleton
    @LanguageListQualifier
    fun provideLanguageList():List<NewsSources>{
        return emptyList()
    }
    @Provides
    @Singleton
    @NewsSourceListQualifier
    fun provideNewsSourceList(): List<NewsSources>{
        return emptyList()
    }
    @Provides
    @Singleton
    fun provideNewsSourceAdapter(
        @NewsSourceListQualifier newsSourceList: List<NewsSources>) : NewsSourceAdaptor{
        return NewsSourceAdaptor(newsSourceList.toMutableList())
    }
    @Provides
    @Singleton
    fun providesSerachList(): List<SearchResponse.Article>{
        return emptyList()
    }
    @Provides
    @Singleton
    fun provideSearchAdapter(searchList: List<SearchResponse.Article>): SearchAdapter{
        return SearchAdapter(searchList.toMutableList())
    }
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }
}