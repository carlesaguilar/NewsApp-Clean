package dev.carlesav.newsapp.feature_news.domain.model

import dev.carlesav.newsapp.feature_news.data.model.NewsDTO

data class News(
    var title: String,
    var content: String,
    var author: String,
    var url: String,
    var imageUrl: String
)

fun NewsDTO.toDomain() = News(
    title = title,
    content = content ?: "",
    author = author ?: "",
    url = url,
    imageUrl = imageUrl
)