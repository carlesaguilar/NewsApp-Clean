package dev.carlesav.newsapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.carlesav.newsapp.feature_news.presentation.MainActivity
import dev.carlesav.newsapp.feature_news.presentation.news.NewsListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NewsListScreenTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            NewsListScreen(navController = rememberNavController())
        }
    }

    @Test
    fun itemsAddedToScreen() = runBlockingTest {
        composeTestRule.onNodeWithText("Title1").assertExists()
        composeTestRule.onNodeWithText("Title2").assertExists()
    }
}