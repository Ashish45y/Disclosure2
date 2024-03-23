package dev.ashish.disclosure.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ashish.disclosure.data.model.Country
import dev.ashish.disclosure.data.repository.CountryListRepository
import dev.ashish.disclosure.ui.base.UiState
import dev.ashish.disclosure.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val countryListRepository: CountryListRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _countryUiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val countryUiState: StateFlow<UiState<List<Country>>> = _countryUiState

    init {
        fetchCountry()
    }

    fun fetchCountry() {
        viewModelScope.launch(dispatcherProvider.main) {
            countryListRepository.getCountry()
                .flowOn(dispatcherProvider.default)
                .catch { e ->
                    _countryUiState.value = UiState.Error(e.toString())
                }.collect {
                    _countryUiState.value = UiState.Success(it)
                }
        }
    }
}