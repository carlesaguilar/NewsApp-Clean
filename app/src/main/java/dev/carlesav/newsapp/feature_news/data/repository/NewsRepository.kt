package dev.carlesav.newsapp.feature_news.data.repository

import dev.carlesav.newsapp.core.Resource
import dev.carlesav.newsapp.feature_news.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(country: String): Flow<Resource<List<News>>>
    fun getNew(title: String): News
}