package dev.carlesav.newsapp.feature_news.domain.repository

import dev.carlesav.newsapp.core.Resource
import dev.carlesav.newsapp.feature_news.data.provider.NewsApi
import dev.carlesav.newsapp.feature_news.data.repository.NewsRepository
import dev.carlesav.newsapp.feature_news.domain.exceptions.ApiKeyInvalidException
import dev.carlesav.newsapp.feature_news.domain.exceptions.ApiKeyMissingException
import dev.carlesav.newsapp.feature_news.domain.model.News
import dev.carlesav.newsapp.feature_news.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    private var news: List<News> = emptyList()

    override fun getNews(country: String): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())

        val apiResponse = newsApi.getTopHeadlines(country)

        try {
            if (!apiResponse.isSuccessful) {
                val jsonError = org.json.JSONObject(apiResponse.errorBody()?.string())
                when (jsonError.get("code")) {
                    "apiKeyMissing" -> throw ApiKeyMissingException()
                    "apiKeyInvalid" -> throw ApiKeyInvalidException()
                    else -> throw Exception()
                }
            } else {
                news = apiResponse.body()?.articles?.map { it.toDomain() } ?: emptyList()
                emit(Resource.Success(news))
            }
        } catch (ex: Exception) {
            val errorMsg = when (ex) {
                is ApiKeyMissingException -> "api key missing"
                is ApiKeyInvalidException -> "api key invalid"
                else -> "Unknown error"
            }
            emit(Resource.Error(errorMsg))
        }
    }

    override fun getNew(title: String): News = news.first { it.title == title }
}