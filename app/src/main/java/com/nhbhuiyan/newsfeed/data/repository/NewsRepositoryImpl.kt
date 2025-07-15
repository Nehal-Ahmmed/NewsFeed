package com.nhbhuiyan.newsfeed.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nhbhuiyan.newsfeed.data.remote.NewsPagingSource
import com.nhbhuiyan.newsfeed.data.remote.SearchNewsPagingSource
import com.nhbhuiyan.newsfeed.data.remote.dto.NewsApi
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(sources: List<String>,searchQuery: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }
}