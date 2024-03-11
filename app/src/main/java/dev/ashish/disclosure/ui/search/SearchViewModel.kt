package dev.ashish.disclosure.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ashish.disclosure.data.model.SearchResponse
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<SearchResponse.Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<SearchResponse.Article>>> = _uiState

    fun performSearch(query: String) {
        try {
            viewModelScope.launch {
                val searchResponse = searchRepository.getSearchResponse(query)
                searchResponse.collect {
                    _uiState.value = UiState.Success(it.articles!!)
                }
            }

        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.toString())
        }
    }
}
