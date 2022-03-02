package dev.carlesav.newsapp

sealed class Destinations(val route: String) {
    object NewsListScreen : Destinations("news_list_screen")
    object NewsDetailScreen : Destinations("news_detail_screen")
}