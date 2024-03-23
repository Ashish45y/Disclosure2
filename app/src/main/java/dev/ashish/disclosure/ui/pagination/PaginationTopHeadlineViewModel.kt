package dev.ashish.disclosure.ui.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ashish.disclosure.data.model.topheadlines.ApiArticle
import dev.ashish.disclosure.data.repository.PaginationTopHeadlineRepository
import dev.ashish.disclosure.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaginationTopHeadlineViewModel @Inject constructor(
    private val paginationTopHeadlineRepository: PaginationTopHeadlineRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _topHeadlineUiState = MutableStateFlow<PagingData<ApiArticle>>(value = PagingData.empty())

    val topHeadlineUiState: StateFlow<PagingData<ApiArticle>> = _topHeadlineUiState

    init {
        startFetchingArticles()
    }

    private fun startFetchingArticles() {
        viewModelScope.launch(dispatcherProvider.main) {
            paginationTopHeadlineRepository.getTopHeadlinesArticles()
                .flowOn(dispatcherProvider.io)
                .catch { e -> }
                .collect {
                    _topHeadlineUiState.value = it
                }
        }
    }

}