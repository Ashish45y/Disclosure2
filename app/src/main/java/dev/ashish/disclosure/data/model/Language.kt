package dev.ashish.disclosure.data.model

data class Language(
    val id: String? = null, val name: String =""
)
data class SelectionState(
    val selectedLanguage: List<Language> = emptyList()
)