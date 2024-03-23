package dev.ashish.disclosure.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.local.DatabaseService
import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.data.model.topheadlines.ApiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class OfflineTopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun getTopHeadlinesArticles(countryID: String): Flow<List<ApiArticle>> {
        return flow { emit(networkService.getTopHeadlines(countryID)) }
            .map {
                it.apiArticles
            }
    }

    fun deleteAndInsertAllTopHeadlinesArticles(articles: List<Article>, country: String) {
        databaseService.deleteAndInsertAllTopHeadlinesArticles(articles, country)
    }

    fun getTopHeadlinesArticlesFromDB(countryID: String): Flow<List<Article>> {
        return databaseService.getAllTopHeadlinesArticles(countryID)
    }
}