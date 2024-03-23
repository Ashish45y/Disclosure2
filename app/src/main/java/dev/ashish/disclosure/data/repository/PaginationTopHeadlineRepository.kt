package dev.ashish.disclosure.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.model.topheadlines.ApiArticle
import dev.ashish.disclosure.utils.AppConstant.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PaginationTopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlinesArticles(): Flow<PagingData<ApiArticle>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { TopHeadlinePagingSource(networkService)
            }).flow
    }
}