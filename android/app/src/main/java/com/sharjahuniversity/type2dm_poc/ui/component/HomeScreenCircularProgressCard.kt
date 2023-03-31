package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.data.model.CircularProgressbar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Utils

@Composable
fun HomeScreenCircularProgressCardItem(
    circularProgressbar: CircularProgressbar,
    shape: RoundedCornerShape = RoundedCornerShapes.medium as RoundedCornerShape
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = circularProgressbar.title,
                color = HomeScreenCardItemsTitle,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500)
            )
            Box(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
            ) {
                DrawCustomCircularProgressbar(
                    circularProgressbar = circularProgressbar
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    val progress = Utils.animateFloatValue(
                        value = circularProgressbar.value
                    )
                    Text(
                        text = progress.toInt().toString(),
                        color = HomeScreenCardItemsTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.height(22.dp)
                    )
                    Text(
                        text = circularProgressbar.scaleType,
                        color = HomeScreenCardItemsSubtitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.height(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenCircularProgressCardItemPreview() {
    HomeScreenCircularProgressCardItem(
        CircularProgressbar(
            title = "Burn Kcal",
            value = 1162f,
            totalValue = 1800f,
            progressbarColorBrush = ProgressbarIndicatorColorBlueBrush,
            progressbarTrackerColor = ProgressbarTrackerIndicatorColorBlue,
            titleColor = ProgressbarIndicatorColorBlueDark,
            scaleType = "Kcal",
            radius = 90.dp
        )
    )
}