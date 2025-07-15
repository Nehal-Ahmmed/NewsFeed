package com.nhbhuiyan.newsfeed.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nhbhuiyan.newsfeed.data.remote.dto.NewsApi
import com.nhbhuiyan.newsfeed.data.remote.dto.NewsResponse
import com.nhbhuiyan.newsfeed.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    var totalNewsCount = 0
        private set

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val newsResponse: NewsResponse = newsApi.searchNews(sources = sources, page = page, searchQuery = searchQuery)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}