package dev.carlesav.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.carlesav.newsapp.feature_news.data.repository.NewsRepository
import dev.carlesav.newsapp.feature_news.domain.use_case.GetNewDetailUseCase
import dev.carlesav.newsapp.feature_news.domain.use_case.GetNewsUseCase
import dev.carlesav.newsapp.feature_news.domain.use_case.NewsUseCases

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNewsUseCase(newsRepository),
            getNewDetail = GetNewDetailUseCase(newsRepository)
        )
    }
}