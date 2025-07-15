package com.nhbhuiyan.newsfeed.presentation.screenSearch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nhbhuiyan.newsfeed.domain.usecase.news.NewsUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUsecases: NewsUsecases
): ViewModel() {
    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> =_state

    fun OnEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSearchQuery -> {
                _state.value= state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchNews ->{
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles= newsUsecases.searchNews(
            sources = listOf("bbc-news","abc-news","al-jazeera-english"),
            searchQuery = state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}