package dev.carlesav.newsapp.feature_news.domain.use_case

import dev.carlesav.newsapp.feature_news.data.repository.NewsRepository
import dev.carlesav.newsapp.feature_news.domain.model.News
import javax.inject.Inject

class GetNewDetailUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(title: String): News {
        return repository.getNew(title)
    }
}