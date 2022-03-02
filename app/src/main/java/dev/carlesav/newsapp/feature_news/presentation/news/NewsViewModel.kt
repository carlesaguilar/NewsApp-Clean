package dev.carlesav.newsapp.feature_news.presentation.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.newsapp.core.Constants.NEWS_LANGUAGE
import dev.carlesav.newsapp.core.Resource
import dev.carlesav.newsapp.core.Utils
import dev.carlesav.newsapp.feature_news.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNews()
    }

    private fun getNews() {
        getNewsUseCase(NEWS_LANGUAGE).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
                is Resource.Success -> {
                    Utils.log("*** Success " + result.data?.size)
                    _state.value = NewsListState(articles = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        NewsListState(error = result.message ?: "An unexpected error occured.")
                }
            }
        }.launchIn(viewModelScope)
    }
}