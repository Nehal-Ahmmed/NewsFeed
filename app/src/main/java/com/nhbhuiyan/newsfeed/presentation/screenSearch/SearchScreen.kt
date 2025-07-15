package com.nhbhuiyan.newsfeed.presentation.screenSearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.presentation.Dimens.mediumPadding1
import com.nhbhuiyan.newsfeed.presentation.common.ArticlesList
import com.nhbhuiyan.newsfeed.presentation.common.SearchBar
import com.nhbhuiyan.newsfeed.presentation.nvgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding1, end = mediumPadding1, start = mediumPadding1)
            .statusBarsPadding()
    ) {
    SearchBar(
        text = state.searchQuery,
        readOnly = false,
        onValueChange = { event(SearchEvent.UpdateSearchQuery(it))},
        onSearch = {event(SearchEvent.SearchNews)}
    )
        Spacer(Modifier.height(mediumPadding1))

        state.articles?.let {
            val articles= it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = {navigateToDetails(it)}
            )
        }
    }
}