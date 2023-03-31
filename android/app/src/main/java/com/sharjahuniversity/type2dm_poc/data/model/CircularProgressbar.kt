package com.sharjahuniversity.type2dm_poc.data.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class CircularProgressbar(
    val title: String,
    val value: Float,
    val totalValue: Float,
    val progressbarColorBrush: Brush,
    val progressbarTrackerColor: Color,
    val titleColor: Color,
    val scaleType: String,
    val radius: Dp
)
