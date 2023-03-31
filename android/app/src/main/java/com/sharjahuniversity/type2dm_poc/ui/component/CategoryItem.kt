package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.IconDetails
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Utils

@Composable
fun CategoryItem(
    title: String,
    icon: IconDetails,
    isSelected: Boolean,
    iconColorDefault: Color,
    iconColorSelected: Color = White,
    backgroundColorBrushDefault: Brush = WhiteBrush,
    backgroundColorBrushSelected: Brush,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.Transparent)

    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShapes.medium)
                .background(
                    brush = if (isSelected) backgroundColorBrushSelected else backgroundColorBrushDefault
                )
                .clickable(onClick = onClick)
        ) {
            if (icon.url.isEmpty() || icon.url.isBlank() || icon.url.isNullOrEmpty()) {
                Icon(
                    painter = painterResource(id = icon.iconId),
                    contentDescription = title,
                    tint = if (isSelected) iconColorSelected else iconColorDefault,
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.Center)
                )
            } else {
                Icon(
                    painter = Utils.loadSVGImage(icon = icon),
                    contentDescription = title,
                    tint = if (isSelected) iconColorSelected else iconColorDefault,
                    modifier = Modifier
                        .size(64.dp)
                        .wrapContentSize()
                )
            }
        }
        Text(
            text = title,
            fontWeight = FontWeight(500),
            fontSize = 10.sp,
            lineHeight = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Transparent)
                .padding(vertical = 8.dp)
                .widthIn(max = 70.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        title = "BreakfastBreakfastBreakfast",
        icon = IconDetails("", iconId = R.drawable.ic_breakfast),
        isSelected = true,
        iconColorDefault = FoodScreenCyanColor,
        backgroundColorBrushSelected = FoodScreenBrush,
        onClick = {}
    )
}