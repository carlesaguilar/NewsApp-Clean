package dev.carlesav.newsapp.di

import dev.carlesav.newsapp.core.Resource
import dev.carlesav.newsapp.feature_news.data.repository.NewsRepository
import dev.carlesav.newsapp.feature_news.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNewsRepositoryImpl : NewsRepository {
    private val news = listOf(
        News("Title1", "Content1", "Author1", "Url1", "urlImage1"),
        News("Title2", "Content2", "Author2", "Url2", "urlImage2")
    )

    override fun getNews(country: String): Flow<Resource<List<News>>> = flow {
        emit(Resource.Success(news))
    }

    override fun getNew(title: String): News = news[0]
}