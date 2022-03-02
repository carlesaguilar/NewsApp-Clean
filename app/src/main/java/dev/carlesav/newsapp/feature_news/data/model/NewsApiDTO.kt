package dev.carlesav.newsapp.feature_news.data.model

import com.google.gson.annotations.SerializedName

class NewsApiDTO(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: String? = null,
    val articles: List<NewsDTO>? = null
)