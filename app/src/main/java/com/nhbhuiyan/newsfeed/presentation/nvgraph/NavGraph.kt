package com.nhbhuiyan.newsfeed.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.nhbhuiyan.newsfeed.presentation.Onboarding.OnboardingScreen
import com.nhbhuiyan.newsfeed.presentation.Onboarding.OnboardingViewModel
import com.nhbhuiyan.newsfeed.presentation.news_navigator.Newsnavigator
import com.nhbhuiyan.newsfeed.presentation.screenBookmark.BookMarkViewModel
import com.nhbhuiyan.newsfeed.presentation.screenBookmark.BookmarkScreen

@Composable
fun NavGraph(startDestination: String) {
    val navController= rememberNavController()

     NavHost(navController = navController, startDestination = startDestination) {

         //navigation grouping 1
         navigation(route = Route.AppStartNavigation.route, //name of this nav part
             startDestination = Route.OnBoardingScreen.route){
             //1
             composable(
                 route=Route.OnBoardingScreen.route //name of this route
             ){
                 val viewModel : OnboardingViewModel = hiltViewModel()
                 OnboardingScreen(event = viewModel::onEvent)
             }
             //2
         }

         //navigation grouping 2
         navigation(route=Route.NewsNavigation.route,   //name of this nav part
             startDestination = Route.NewsNavigatorScreen.route){
             //1
             composable(route=Route.NewsNavigatorScreen.route){//name of this route
                Newsnavigator()
             }
             //2

         }
     }
}