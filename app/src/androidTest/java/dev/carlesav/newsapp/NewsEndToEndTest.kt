package dev.carlesav.newsapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dev.carlesav.newsapp.core.TestTags
import dev.carlesav.newsapp.di.AppModule
import dev.carlesav.newsapp.feature_news.presentation.MainActivity
import dev.carlesav.newsapp.feature_news.presentation.details.DetailsScreen
import dev.carlesav.newsapp.feature_news.presentation.news.NewsListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NewsEndToEndTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Destinations.NewsListScreen.route,
            ) {
                composable(Destinations.NewsListScreen.route) {
                    NewsListScreen(navController)
                }
                composable("${Destinations.NewsDetailScreen.route}/{newTitle}") {
                    it.arguments?.getString("newTitle")?.let { title ->
                        DetailsScreen(title, navController)
                    }
                }
            }
        }
    }

    @Test
    fun navigateToArticle() {
        composeRule
            .onNodeWithText("Title1")
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.DETAIL_TITLE)
            .assertExists()
    }
}