package dev.ashish.disclosure.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.ashish.disclosure.DisclosureApplication
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.BaseUrl
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

}