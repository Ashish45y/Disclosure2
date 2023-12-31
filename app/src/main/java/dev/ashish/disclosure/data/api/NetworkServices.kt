package dev.ashish.disclosure.data.api

import dev.ashish.disclosure.data.model.NewsSourcesResponse
import dev.ashish.disclosure.data.model.SearchResponse
import dev.ashish.disclosure.data.model.TopHeadlinesResponse
import dev.ashish.disclosure.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getSearchResponse(@Query("q") q:String) : SearchResponse
}