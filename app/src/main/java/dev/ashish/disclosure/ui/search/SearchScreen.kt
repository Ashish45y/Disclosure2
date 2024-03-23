package dev.ashish.disclosure.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ashish.disclosure.R
import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.ui.activity.ui.theme.gray40
import dev.ashish.disclosure.ui.base.ArticleList
import dev.ashish.disclosure.ui.base.ShowError
import dev.ashish.disclosure.ui.base.ShowLoading
import dev.ashish.disclosure.ui.base.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenRoute(
    onNewsClick: (url: String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchUiState: UiState<List<Article>> by searchViewModel.searchUiState.collectAsStateWithLifecycle()

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(
        modifier =
        Modifier
            .background(gray40)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(query = text, onQueryChange = {
            text = it
            searchViewModel.searchNews(it)
        }, onSearch = {
            active = false
        }, active = active, placeholder = {
            Text(text = stringResource(R.string.search_news))
        }, leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        }, content = {
            SearchNewsList(searchUiState, onNewsClick, searchViewModel)
        }, onActiveChange = {
            active = it
        }, tonalElevation = 0.dp
        )
    }
}

@Composable
fun SearchNewsList(
    uiState: UiState<List<Article>>,
    onNewsClick: (url: String) -> Unit,
    searchViewModel: SearchViewModel
) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message) {
                searchViewModel.createNewsFlow()
            }
        }
    }
}