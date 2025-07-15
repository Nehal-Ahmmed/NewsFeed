package com.nhbhuiyan.newsfeed.presentation.details

import com.nhbhuiyan.newsfeed.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemovingSideEffecr : DetailsEvent()
}