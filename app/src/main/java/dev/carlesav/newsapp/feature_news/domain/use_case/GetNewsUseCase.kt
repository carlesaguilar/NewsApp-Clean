package dev.carlesav.newsapp.feature_news.domain.use_case

import dev.carlesav.newsapp.core.Resource
import dev.carlesav.newsapp.feature_news.data.repository.NewsRepository
import dev.carlesav.newsapp.feature_news.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(country: String): Flow<Resource<List<News>>> {
        return repository.getNews(country)
    }
}