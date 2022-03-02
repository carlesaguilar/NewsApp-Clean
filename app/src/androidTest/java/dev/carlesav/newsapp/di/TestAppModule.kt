package dev.carlesav.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.carlesav.newsapp.feature_news.data.repository.NewsRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class TestAppModule {

    @Provides
    @Singleton
    fun providerNewsRepository(): NewsRepository = FakeNewsRepositoryImpl()
}