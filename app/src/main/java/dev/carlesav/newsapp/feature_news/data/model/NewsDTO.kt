package dev.carlesav.newsapp.feature_news.data.model

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    @SerializedName("title")
    var title: String,
    @SerializedName("content")
    var content: String?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("url")
    var url: String,
    @SerializedName("urlToImage")
    var imageUrl: String
)