package com.nhbhuiyan.newsfeed.domain.usecase.news

import androidx.paging.PagingData
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(private val repository: NewsRepository) {
    operator fun invoke(sources: List<String>, searchQuery: String) : Flow<PagingData<Article>>{
        return repository.searchNews(
            sources = sources,
            searchQuery = searchQuery
        )
    }
}