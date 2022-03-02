package dev.carlesav.newsapp.feature_news.domain.use_case

data class NewsUseCases(
    val getNews: GetNewsUseCase,
    val getNewDetail: GetNewDetailUseCase
)