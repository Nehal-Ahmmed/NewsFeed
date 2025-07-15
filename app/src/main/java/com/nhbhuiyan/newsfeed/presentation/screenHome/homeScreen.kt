package com.nhbhuiyan.newsfeed.presentation.screenHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.nhbhuiyan.newsfeed.R
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.presentation.Dimens.mediumPadding1
import com.nhbhuiyan.newsfeed.presentation.common.ArticlesList
import com.nhbhuiyan.newsfeed.presentation.common.SearchBar
import com.nhbhuiyan.newsfeed.presentation.nvgraph.Route
@Composable
fun homeScreen(articles: LazyPagingItems<Article>, navigateToSearch: () -> Unit, navigateToDetails: (Article) -> Unit) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = mediumPadding1)
        )
        Spacer(Modifier.height(mediumPadding1))

        SearchBar(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {})

        Spacer(Modifier.height(mediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = mediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(R.color.placeholder)
        )
        Spacer(Modifier.height(mediumPadding1))

        ArticlesList(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}