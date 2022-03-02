package dev.carlesav.newsapp

import dev.carlesav.newsapp.core.Constants
import dev.carlesav.newsapp.core.Resource
import dev.carlesav.newsapp.feature_news.data.provider.NewsApi
import dev.carlesav.newsapp.feature_news.domain.repository.NewsRepositoryImpl
import dev.carlesav.newsapp.feature_news.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

class NewsRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val newsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    private val newsRepository = NewsRepositoryImpl(newsProvider)
    private val getNewsUseCase = GetNewsUseCase(newsRepository)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get top headlines`() = runBlocking {
        mockWebServer.enqueueResponse("top_headlines.json")
        getNewsUseCase(Constants.NEWS_LANGUAGE).take(1).collect { response ->
            if (response is Resource.Success) {
                val articles = response.data
                assertEquals(2, articles?.size)
                assertEquals("Rob Merrick", articles?.get(0)?.author)
                assertEquals("Sophie Morris", articles?.get(1)?.author)
            }
        }
    }

    @Test
    fun `Api key missing exception`() {
        mockWebServer.enqueueResponse("api_key_missing.json")

        runBlocking {
            newsRepository.getNews(Constants.NEWS_LANGUAGE).take(1).collect { response ->
                if (response is Resource.Error) {
                    assertEquals("api key missing", response.message)
                }
            }
        }
    }

    @Test
    fun `Api Key Invalid exception`() {
        mockWebServer.enqueueResponse("api_key_invalid.json")

        runBlocking {
            newsRepository.getNews(Constants.NEWS_LANGUAGE).take(1).collect { response ->
                if (response is Resource.Error) {
                    assertEquals("api key invalid", response.message)
                }
            }
        }
    }
}

fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}