package dev.ashish.disclosure.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourceViewModel @Inject constructor(private val newsSourceRepo : Repository): ViewModel(){
    private val _uiState = MutableStateFlow<UiState<List<NewsSources>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsSources>>> = _uiState
    init {
        fetchNewsSource()
    }
    private fun fetchNewsSource() {
        viewModelScope.launch {
            newsSourceRepo.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                   _uiState.value = UiState.Success(it)
                }
        }
    }
}