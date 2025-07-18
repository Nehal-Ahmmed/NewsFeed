package com.nhbhuiyan.newsfeed.data.remote.dto

import com.nhbhuiyan.newsfeed.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)