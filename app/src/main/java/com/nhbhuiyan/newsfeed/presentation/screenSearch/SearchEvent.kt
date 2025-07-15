package com.nhbhuiyan.newsfeed.presentation.screenSearch

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    object SearchNews : SearchEvent()
}