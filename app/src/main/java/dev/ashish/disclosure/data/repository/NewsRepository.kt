package dev.ashish.disclosure.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.local.DatabaseService
import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.data.model.topheadlines.toArticleEntity
import dev.ashish.disclosure.data.model.topheadlines.toArticleLanguage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun getNewsBySources(sourceId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsBySources(sourceId)) }
            .map {
                it.apiArticles.map { apiArticle -> apiArticle.toArticleEntity(sourceId) }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAllSourceNews(articles, sourceId)) }
            }.flatMapConcat {
                databaseService.getSourceNewsByDB(sourceId)
            }
    }

    fun getNewsBySourceByDB(sourceId: String): Flow<List<Article>> {
        return databaseService.getSourceNewsByDB(sourceId)
    }


    fun getNewsByCountry(countryId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsByCountry(countryId)) }
            .map {
                it.apiArticles.map { apiArticle -> apiArticle.toArticleEntity(countryId) }
            }.flatMapConcat { articles ->
                flow {
                    emit(
                        databaseService.deleteAndInsertAllTopHeadlinesArticles(articles, countryId)
                    )
                }
            }.flatMapConcat {
                databaseService.getAllTopHeadlinesArticles(countryId)
            }
    }

    fun getNewsByCountryByDB(countryId: String): Flow<List<Article>> {
        return databaseService.getAllTopHeadlinesArticles(countryId)
    }

    fun getNewsByLanguage(languageId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsByLanguage(languageId)) }
            .map {
                it.apiArticles.map { apiArticle -> apiArticle.toArticleLanguage(languageId) }
            }.flatMapConcat { articles ->
                flow {
                    emit(
                        databaseService.deleteAllAndInsertAllLanguageArticles(articles, languageId)
                    )
                }
            }.flatMapConcat {
                databaseService.getLanguageNews(languageId)
            }
    }

    fun getNewsByLanguageByDB(languageId: String): Flow<List<Article>> {
        return databaseService.getLanguageNews(languageId)
    }
}