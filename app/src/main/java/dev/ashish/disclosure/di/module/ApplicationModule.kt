package dev.ashish.disclosure.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.ashish.disclosure.DisclosureApplication
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.BaseUrl
import dev.ashish.disclosure.di.FragmentScope
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
        // You can provide the articles in this method
        return emptyList()
    }
    @Provides
    @Singleton
    fun providesTopHeadlineAdapter(articleList: List<Article>): TopHeadlineAdapter {
        return TopHeadlineAdapter(articleList.toMutableList())
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