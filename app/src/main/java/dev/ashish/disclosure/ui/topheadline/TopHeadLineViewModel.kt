package dev.ashish.disclosure.ui.topheadline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.di.FragmentScope
import dev.ashish.disclosure.ui.base.UiState
import dev.ashish.disclosure.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
@FragmentScope
class TopHeadlineViewModel @Inject constructor(private val topHeadlineRepository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchNews()
        fetchNewsSource()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
    private fun fetchNewsSource() {
        viewModelScope.launch {
            topHeadlineRepository.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    Log.d("Ashish", "fetchNewsSource: ${it.size}")
                }
        }
    }

}