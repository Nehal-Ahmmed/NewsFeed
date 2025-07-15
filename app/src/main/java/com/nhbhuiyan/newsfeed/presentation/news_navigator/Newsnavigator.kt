package com.nhbhuiyan.newsfeed.presentation.news_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.nhbhuiyan.newsfeed.R
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.presentation.details.DetailsScreen
import com.nhbhuiyan.newsfeed.presentation.details.DetailsViewModel
import com.nhbhuiyan.newsfeed.presentation.news_navigator.components.BottomNavItem
import com.nhbhuiyan.newsfeed.presentation.news_navigator.components.NewsBottomNavigation
import com.nhbhuiyan.newsfeed.presentation.nvgraph.Route
import com.nhbhuiyan.newsfeed.presentation.screenBookmark.BookMarkViewModel
import com.nhbhuiyan.newsfeed.presentation.screenBookmark.BookmarkScreen
import com.nhbhuiyan.newsfeed.presentation.screenHome.homeScreen
import com.nhbhuiyan.newsfeed.presentation.screenHome.homeVIewModel
import com.nhbhuiyan.newsfeed.presentation.screenSearch.SearchScreen
import com.nhbhuiyan.newsfeed.presentation.screenSearch.SearchViewModel

@Composable
fun Newsnavigator() {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavItem(icon = R.drawable.ic_bookmark, text = "BookMark"),
        )
    }

    val navController = rememberNavController()
    var backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItem,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(navController = navController, Route.HomeScreen.route)
                        1 -> navigateToTab(navController = navController, Route.SearchScreen.route)
                        2 -> navigateToTab(
                            navController = navController,
                            Route.BookmarkScreen.route
                        )

                        else -> Unit
                    }
                })
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: homeVIewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                homeScreen(
                    articles,
                    navigateToSearch = {
                        navigateToTab(navController = navController, tab = Route.SearchScreen.route)
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::OnEvent, navigateToDetails = {
                    navigateToDetails(
                        navController = navController,
                        article = it
                    )
                })
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                //TODO: Handle side effect
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )

                    }
            }

            composable(route= Route.BookmarkScreen.route){
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state= viewModel.state.value
                BookmarkScreen(
                    state=state,
                    navigateToDetails = {article ->  
                        navigateToDetails(navController=navController, article = article)
                    }
                )
            }
        }
    }
}

fun navigateToTab(navController: NavController, tab: String) {
    navController.navigate(route = tab) {
        navController.graph.startDestinationRoute?.let { homeRoute ->
            popUpTo(homeRoute) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "article",
        value = article
    )
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}
