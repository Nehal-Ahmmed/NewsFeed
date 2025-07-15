package com.nhbhuiyan.newsfeed.domain.usecase.news

import androidx.paging.PagingData
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String>) : Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = sources)
    }
}