package dev.ashish.disclosure.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.di.ActivityContext
import dev.ashish.disclosure.ui.topheadline.TopHeadlineAdapter

@Module
class FragmentModule(private val fragment: Fragment) {
    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.requireContext()

    @Provides
    fun provideTopHeadlineAdapter(articleList: MutableList<Article>): TopHeadlineAdapter {
        return TopHeadlineAdapter(articleList)
    }
    @Provides
    fun provideArticleList(): List<Article> {
        return listOf()
    }
    }