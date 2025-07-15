package com.nhbhuiyan.newsfeed.presentation.news_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhbhuiyan.newsfeed.R
import com.nhbhuiyan.newsfeed.presentation.Dimens.extraSmallPadding2
import com.nhbhuiyan.newsfeed.presentation.Dimens.smallIconSize
import com.nhbhuiyan.newsfeed.ui.theme.NewsFeedTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column (verticalArrangement = Arrangement.Center){
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(smallIconSize)
                        )
                        Spacer(Modifier.height(extraSmallPadding2))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                )
            )
        }
    }
}

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview
@Composable
fun previewNewsnav() {
    NewsFeedTheme {
        NewsBottomNavigation(items = listOf(
            BottomNavItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavItem(icon = R.drawable.ic_search, text = "Search"),
        ),
            selectedItem = 0,
            onItemClick = {})
    }
}