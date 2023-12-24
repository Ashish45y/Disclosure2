package dev.ashish.disclosure.data.repository

import android.util.Log
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.di.FragmentScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
    fun getNewsSources():Flow<List<NewsSources>>{
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            Log.d("ashish", "getNewsSources:$it ")
            it.sources
        }
    }
}
