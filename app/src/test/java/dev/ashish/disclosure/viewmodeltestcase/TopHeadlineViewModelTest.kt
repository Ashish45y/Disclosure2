package dev.ashish.disclosure.viewmodeltestcase

import app.cash.turbine.test
import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.data.repository.TopHeadlineRepository
import dev.ashish.disclosure.ui.base.UiState
import dev.ashish.disclosure.ui.topheadline.TopHeadlineViewModel
import dev.ashish.disclosure.utils.AppConstant
import dev.ashish.disclosure.utils.DispatcherProvider
import dev.ashish.disclosure.utils.LogTest
import dev.ashish.disclosure.utils.NetworkHelper
import dev.ashish.disclosure.utils.NetworkHelperTest
import dev.ashish.disclosure.utils.TestDispatcherProvider
import dev.ashish.disclosure.utils.logger.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineViewModelTest {
    @Mock
    private lateinit var topHeadlineRepository: TopHeadlineRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    private lateinit var networkHelperTest: NetworkHelper

    private lateinit var logTest: Logger

    @Before
    fun setup() {
        dispatcherProvider = TestDispatcherProvider()
        networkHelperTest = NetworkHelperTest()
        logTest = LogTest()
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            val country = AppConstant.COUNTRY
            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getTopHeadlinesArticles(country)
            val viewmodel = TopHeadlineViewModel(
                topHeadlineRepository,
                dispatcherProvider,
                networkHelperTest,
                logTest
            )
            viewmodel.topHeadlineUiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlinesArticles(country)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetErrorUiState() {
        runTest {
            val country = AppConstant.COUNTRY
            val errorMsg = "Error msg"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMsg)
            })
                .`when`(topHeadlineRepository)
                .getTopHeadlinesArticles(country)
            val viewModel = TopHeadlineViewModel(
                topHeadlineRepository,
                dispatcherProvider,
                networkHelperTest,
                logTest
            )
            viewModel.topHeadlineUiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMsg).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlinesArticles(country)
        }
    }
}