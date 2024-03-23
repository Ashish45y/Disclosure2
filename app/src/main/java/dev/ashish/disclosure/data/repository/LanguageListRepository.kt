package dev.ashish.disclosure.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dev.ashish.disclosure.data.model.Language
import dev.ashish.disclosure.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class LanguageListRepository @Inject constructor() {

    fun getLanguages(): Flow<List<Language>> {
        return flow { emit(AppConstant.LANGUAGES) }
    }

}
