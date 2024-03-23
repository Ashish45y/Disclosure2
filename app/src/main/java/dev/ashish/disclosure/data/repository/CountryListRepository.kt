package dev.ashish.disclosure.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.model.Country
import dev.ashish.disclosure.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CountryListRepository @Inject constructor() {

    fun getCountry(): Flow<List<Country>> {
        return flow { emit(AppConstant.COUNTRIES) }
    }
}