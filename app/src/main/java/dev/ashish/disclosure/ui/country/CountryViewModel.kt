package dev.ashish.disclosure.ui.country

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

class CountryViewModel @Inject constructor(private val countryRepo: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<NewsSources>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsSources>>> = _uiState

    init {
        fetchCountryList()
    }

    private fun fetchCountryList() {
        viewModelScope.launch {
            countryRepo.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}