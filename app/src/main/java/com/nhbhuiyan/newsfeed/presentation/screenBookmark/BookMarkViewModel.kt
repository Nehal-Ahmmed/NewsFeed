package com.nhbhuiyan.newsfeed.presentation.screenBookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhbhuiyan.newsfeed.domain.usecase.news.NewsUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val newsUsecases: NewsUsecases
): ViewModel() {
    private val _state = mutableStateOf(BookmarkState())
    val state : State<BookmarkState> = _state

    init {
        getArticles()
    }

    private fun getArticles(){
        newsUsecases.selectArticles().onEach {
            _state.value=_state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}