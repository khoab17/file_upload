package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun BackgroundTopColoredBox(
    shape: Shape,
    color: Color
) {
    Box(
        modifier = Modifier
            .clip(shape = shape)
            .fillMaxHeight(.25f)
            .fillMaxWidth()
            .background(color = color)
    )
}