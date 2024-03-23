package dev.ashish.disclosure.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.topheadlines.ApiArticle
import dev.ashish.disclosure.utils.AppConstant.COUNTRY
import dev.ashish.disclosure.utils.AppConstant.INITIAL_PAGE
import dev.ashish.disclosure.utils.AppConstant.PAGE_SIZE

@ViewModelScoped
class TopHeadlinePagingSource(private val networkService: NetworkService) :
    PagingSource<Int, ApiArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val response = networkService.getTopHeadlines(
                country = COUNTRY,
                page = page,
                pageSize = PAGE_SIZE
            )

            LoadResult.Page(
                data = response.apiArticles,
                prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.apiArticles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}