package dev.ashish.disclosure.data.local

import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.data.local.entity.NewsSources
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    fun getAllTopHeadlinesArticles(countryID: String): Flow<List<Article>>

    fun deleteAndInsertAllTopHeadlinesArticles(articles: List<Article>, countryID: String)

    fun getNewsSources(): Flow<List<NewsSources>>

    fun deleteAndInsertAllNewsSources(articles: List<NewsSources>)

    fun getSourceNewsByDB(sourceID: String): Flow<List<Article>>

    fun deleteAllAndInsertAllSourceNews(articles: List<Article>, sourceID: String)

    fun getLanguageNews(languageID: String): Flow<List<Article>>

    fun deleteAllAndInsertAllLanguageArticles(articles: List<Article>, languageID: String)
}