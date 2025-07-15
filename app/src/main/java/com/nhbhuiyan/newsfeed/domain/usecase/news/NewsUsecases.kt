package com.nhbhuiyan.newsfeed.domain.usecase.news

data class NewsUsecases(
    val getNews: GetNews,
    val searchNews: SearchNews,

    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,

    val selectArticle: SelectArticle
)
