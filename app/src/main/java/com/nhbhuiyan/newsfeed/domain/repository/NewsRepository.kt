package com.nhbhuiyan.newsfeed.domain.repository

import androidx.paging.PagingData
import com.nhbhuiyan.newsfeed.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(sources: List<String>, searchQuery : String) : Flow<PagingData<Article>>
}