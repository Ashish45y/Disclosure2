package dev.ashish.disclosure.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.data.model.topheadlines.toArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class SearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsByQueries(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByQueries(query))
        }.map {
            it.apiArticles.map { apiArticle -> apiArticle.toArticleEntity(query) }
        }
    }
}