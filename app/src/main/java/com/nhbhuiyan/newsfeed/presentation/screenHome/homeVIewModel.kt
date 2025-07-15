package com.nhbhuiyan.newsfeed.presentation.screenHome

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nhbhuiyan.newsfeed.domain.usecase.news.NewsUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class homeVIewModel @Inject constructor(
    private val newsUsecases: NewsUsecases
): ViewModel() {
    var state = mutableStateOf(HomeState())
        private set

    val news= newsUsecases.getNews(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)
}