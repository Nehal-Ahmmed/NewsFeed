package com.nhbhuiyan.newsfeed.presentation.screenSearch

import androidx.paging.PagingData
import com.nhbhuiyan.newsfeed.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery : String = "",
    val articles : Flow<PagingData<Article>> ?= null
)
