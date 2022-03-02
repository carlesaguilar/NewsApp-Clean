package dev.carlesav.newsapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.carlesav.newsapp.core.TestTags
import dev.carlesav.newsapp.feature_news.presentation.MainActivity
import dev.carlesav.newsapp.feature_news.presentation.details.DetailsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NewsDetailScreenTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            val navController = rememberNavController()
            DetailsScreen(
                newTitle = "Title1",
                navController = navController
            )
        }
    }

    @Test
    fun isArticleAddedToScreen() {
        composeTestRule.onNodeWithTag(TestTags.DETAIL_TITLE).assertExists()
    }
}