package com.nhbhuiyan.newsfeed.presentation.nvgraph

sealed class Route (
    val route: String
){
    object OnBoardingScreen: Route("onBoardingScreen")
    object HomeScreen: Route("homeScreen")
    object SearchScreen: Route("searchScreen")
    object BookmarkScreen: Route("bookmarkScreen")
    object DetailsScreen : Route("detailsScreen")
    object NewsNavigatorScreen: Route("newsNavigatorScreen")

    object AppStartNavigation: Route("appStartNavigation")
    object NewsNavigation: Route("newsNavigation")
}