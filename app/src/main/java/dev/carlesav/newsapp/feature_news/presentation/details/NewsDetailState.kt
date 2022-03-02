package dev.carlesav.newsapp.feature_news.presentation.details

import dev.carlesav.newsapp.feature_news.domain.model.News

data class NewsDetailState(
    val isLoading: Boolean = false,
    val article: News? = null
)