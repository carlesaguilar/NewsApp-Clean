package dev.carlesav.newsapp.feature_news.data.provider

import dev.carlesav.newsapp.core.Constants.API_KEY
import dev.carlesav.newsapp.feature_news.data.model.NewsApiDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): Response<NewsApiDTO>
}