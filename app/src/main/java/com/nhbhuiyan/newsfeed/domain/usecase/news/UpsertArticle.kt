package com.nhbhuiyan.newsfeed.domain.usecase.news

import com.nhbhuiyan.newsfeed.data.local.NewsDao
import com.nhbhuiyan.newsfeed.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }
}