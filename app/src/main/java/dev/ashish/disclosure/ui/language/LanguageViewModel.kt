package dev.ashish.disclosure.ui.language

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.data.repository.Repository
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageViewModel @Inject constructor(private val LanguageRepo : Repository): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<NewsSources>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsSources>>> = _uiState

    init {
        fetchLanguageList()
    }

    private fun fetchLanguageList() {
        viewModelScope.launch {
            LanguageRepo.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    Log.d("LanguageList", "fetchLanguageList: $it")
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
