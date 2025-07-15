package com.nhbhuiyan.newsfeed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhbhuiyan.newsfeed.domain.usecase.app_entry.AppEntryUsecases
import com.nhbhuiyan.newsfeed.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
private val appEntryUsecases: AppEntryUsecases
) : ViewModel(){

    //this viewModel gives me these things
    var splashCondition by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    //this is the process :the process will be initialised after primary constructor and the initialisation of properties of this class
    //readappentry check-> previously accessed or not-> true or false -> get by .onEach{} and put into shouldstartFromHomeScreen
    //.launchIn(viewModelScope) -> it will run into the background and dissmiss its lifeCycle with the end of this viewModel
    init {
        appEntryUsecases.readAppEntry().onEach {shouldstartFromHomeScreen->
            if(shouldstartFromHomeScreen) startDestination=Route.NewsNavigation.route
            else startDestination=Route.AppStartNavigation.route
            delay(300)
            splashCondition= false
        }.launchIn(viewModelScope)
    }
}