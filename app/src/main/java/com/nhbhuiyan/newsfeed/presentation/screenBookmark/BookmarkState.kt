package com.nhbhuiyan.newsfeed.presentation.screenBookmark

import com.nhbhuiyan.newsfeed.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
