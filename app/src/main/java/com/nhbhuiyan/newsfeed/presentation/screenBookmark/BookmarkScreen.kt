package com.nhbhuiyan.newsfeed.presentation.screenBookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.nhbhuiyan.newsfeed.R
import com.nhbhuiyan.newsfeed.domain.model.Article
import com.nhbhuiyan.newsfeed.presentation.Dimens.mediumPadding1
import com.nhbhuiyan.newsfeed.presentation.common.ArticlesList
import com.nhbhuiyan.newsfeed.presentation.nvgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = mediumPadding1, start = mediumPadding1, end = mediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(mediumPadding1))

        ArticlesList(
            articles = state.articles,
            onClick = {navigateToDetails(it)}
        )
    }
}

@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevBookmarkScreen() {
    BookmarkScreen(state = BookmarkState(), navigateToDetails = {})
}