package dev.ashish.disclosure.data.repository

import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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
            it.sources
        }
    }
    fun getSearchResponse(query: String): Flow<SearchResponse>{
        return flow {
            emit(networkService.getSearchResponse(query))
        }.map {
            it
        }
    }
}
