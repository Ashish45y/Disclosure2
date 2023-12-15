package dev.ashish.disclosure.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.di.ActivityContext
import dev.ashish.disclosure.ui.base.ViewModelProviderFactory
import dev.ashish.disclosure.ui.topheadline.TopHeadlineViewModel


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: Repository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }
}