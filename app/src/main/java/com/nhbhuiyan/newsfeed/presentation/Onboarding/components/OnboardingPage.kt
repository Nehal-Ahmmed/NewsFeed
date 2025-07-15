package com.nhbhuiyan.newsfeed.presentation.Onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.nhbhuiyan.newsfeed.R
import com.nhbhuiyan.newsfeed.presentation.Dimens.mediumPadding1
import com.nhbhuiyan.newsfeed.presentation.Dimens.mediumPadding2
import com.nhbhuiyan.newsfeed.presentation.Onboarding.Page
import com.nhbhuiyan.newsfeed.presentation.Onboarding.pages
import com.nhbhuiyan.newsfeed.ui.theme.NewsFeedTheme

@Composable
fun OnboardingPage(modifier: Modifier = Modifier,page: Page) {
Column {
    Image(
        painter = painterResource(id = page.image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    )

    Spacer(Modifier.height(mediumPadding1))

    Text(
        page.title,
        modifier= modifier
            .padding(horizontal = mediumPadding2),
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
        color = colorResource(R.color.display_small)
    )

    Text(
        page.description,
        modifier= modifier
            .padding(horizontal = mediumPadding2),
        style = MaterialTheme.typography.bodyMedium,
        color = colorResource(R.color.text_medium)
    )
}
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,showBackground = true)
@Composable
fun OnboardingPagePreview(){
    NewsFeedTheme {
        OnboardingPage(page = pages[0])
    }
}