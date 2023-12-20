package dev.ashish.disclosure.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.di.ActivityContext
import dev.ashish.disclosure.di.ApplicationContext
import dev.ashish.disclosure.di.BaseUrl
import dev.ashish.disclosure.di.FragmentScope
import dev.ashish.disclosure.ui.topheadline.TopHeadlineAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class FragmentModule(private val fragment: Fragment) {
    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.requireContext()
    @ApplicationContext
    @Provides
    fun provideApplicationContext(): Context = fragment.requireContext().applicationContext
}