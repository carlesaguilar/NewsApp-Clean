package dev.carlesav.newsapp.feature_news.presentation.news

import dev.carlesav.newsapp.feature_news.domain.model.News

data class NewsListState(
    val isLoading: Boolean = false,
    val articles: List<News> = emptyList(),
    val error: String = ""
)
