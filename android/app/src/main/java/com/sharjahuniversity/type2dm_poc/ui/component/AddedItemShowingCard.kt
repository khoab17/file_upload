package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import java.util.*

@Composable
fun AddedItemShowingCard(
    modifier: Modifier = Modifier,
    title: String,
    quantity: Int,
    unit: String,
    brush: Brush,
    color: Color,
    removeButtonClicked: () -> Unit
) {
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(White, RoundedCornerShapes.medium)
            .fillMaxWidth()
            .heightIn(min = 62.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(0.4f)
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = title,
                color = AddItemTitleColor,
                fontWeight = FontWeight(500),
                lineHeight = 18.sp,
                fontSize = 14.sp,
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .widthIn(min = 76.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = String.format(
                    Locale.ENGLISH,
                    stringResource(id = R.string.added_item_showing_card_quantity),
                    quantity,
                    unit
                ),
                style = TextStyle(
                    textDirection = TextDirection.ContentOrLtr
                ),
                color = AddedItemQuantityColor,
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.widthIn(min = 80.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    //.padding(start = 20.dp)
                    .height(32.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShapes.small
                    )
                    .clip(RoundedCornerShapes.small)
                    .clickable(
                        enabled = true,
                        onClick = {
                            removeButtonClicked()
                        }
                    )
            ) {
                Text(
                    text = stringResource(R.string.remove),
                    color = White,
                    fontWeight = FontWeight(500),
                    lineHeight = 14.sp,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
}

@Preview
@Composable
fun AddedItemShowingCardPreview() {
    AddedItemShowingCard(
        modifier = Modifier,
        "AppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleAppleApple",
        2,
        "unit",
        FoodScreenBrush,
        FoodScreenCyanColor
    ) {}
}