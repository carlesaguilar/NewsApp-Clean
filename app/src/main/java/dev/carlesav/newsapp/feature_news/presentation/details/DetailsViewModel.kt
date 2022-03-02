package dev.carlesav.newsapp.feature_news.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.newsapp.feature_news.domain.use_case.GetNewDetailUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNewDetailUseCase: GetNewDetailUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NewsDetailState())
    val state: State<NewsDetailState> = _state

    fun getNewDetail(title: String) {
        val retrievedNew = getNewDetailUseCase(title)
        _state.value = NewsDetailState(article = retrievedNew)
    }
}