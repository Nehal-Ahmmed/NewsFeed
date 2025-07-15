package com.nhbhuiyan.newsfeed.presentation.Onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.nhbhuiyan.newsfeed.presentation.Dimens.IndicatorSize
import com.nhbhuiyan.newsfeed.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Color.Blue,
    unselectedColor: Color = BlueGray
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(times = pageSize) { page ->
            Box(
                modifier = modifier
                    .size(IndicatorSize)
                    .clip(shape = CircleShape)
                    .background(shape = CircleShape, color = if(page==selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}