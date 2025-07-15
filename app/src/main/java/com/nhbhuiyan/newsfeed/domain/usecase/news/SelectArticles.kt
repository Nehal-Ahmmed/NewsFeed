package com.nhbhuiyan.newsfeed.domain.usecase.news

import com.nhbhuiyan.newsfeed.data.local.NewsDao
import com.nhbhuiyan.newsfeed.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}