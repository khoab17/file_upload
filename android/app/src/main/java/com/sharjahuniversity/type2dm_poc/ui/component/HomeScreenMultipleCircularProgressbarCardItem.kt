package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.sharjahuniversity.type2dm_poc.data.model.CircularProgressbar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Utils
import java.util.*

@Composable
fun HomeScreenMultipleCircularProgressbarCardItem(
    circularProgressbarList: List<CircularProgressbar>,
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
                .padding(
                    vertical = 12.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        top = 30.dp,
                        bottom = 24.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                circularProgressbarList.forEach {
                    DrawCustomCircularProgressbar(
                        circularProgressbar = it
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    circularProgressbarList.forEach {
                        val progress = Utils.animateFloatValue(
                            value = it.value
                        )
                        Text(
                            text = if (
                                it.scaleType == stringResource(
                                    id = R.string.scale_h
                                )
                            ) {
                                String.format(
                                    Locale.ENGLISH,
                                    stringResource(
                                        id = R.string.scale_h_string_format
                                    ), progress
                                )
                            } else {
                                progress.toInt().toString()
                            }
                                    + " " + it.scaleType,
                            color = it.titleColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            style = TextStyle(
                                textDirection = TextDirection.ContentOrLtr
                            ),
                            modifier = Modifier.height(18.dp)
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                modifier = Modifier
                    .padding(
                        bottom = 16.dp,
                        top = 4.dp
                    )
                    .fillMaxWidth()
            ) {
                circularProgressbarList.forEach {
                    HighlightTheTitleWithColor(
                        title = it.title,
                        color = it.titleColor
                    )
                }
            }
        }
    }
}

@Composable
fun HighlightTheTitleWithColor(title: String, color: Color) {
    Row {
        Canvas(
            modifier = Modifier
                .size(10.dp)
                .align(Alignment.CenterVertically)
        ) {
            drawCircle(
                color = color
            )
        }
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 4.dp
                ),
            text = title,
            color = HomeScreenCardItemsTitle,
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenMultipleCircularProgressCardItemPreview() {
    val items = listOf(
        CircularProgressbar(
            title = stringResource(id = R.string.burnt_cal),
            value = 1162f,
            totalValue = 1800f,
            progressbarColorBrush = ProgressbarIndicatorColorBlueBrush,
            progressbarTrackerColor = ProgressbarTrackerIndicatorColorBlue,
            titleColor = ProgressbarIndicatorColorBlueDark,
            scaleType = stringResource(id = R.string.scale_k),
            radius = 180.dp
        ), CircularProgressbar(
            title = stringResource(id = R.string.walk),
            value = 1602f,
            totalValue = 3000f,
            progressbarColorBrush = ProgressbarIndicatorColorCyanBrush,
            progressbarTrackerColor = ProgressbarTrackerIndicatorColorCyan,
            titleColor = ProgressbarIndicatorColorCyanDark,
            scaleType = stringResource(id = R.string.scale_s),
            radius = 140.dp
        ), CircularProgressbar(
            title = stringResource(id = R.string.sleep),
            value = 6f,
            totalValue = 8f,
            progressbarColorBrush = ProgressbarIndicatorColorGreenBrush,
            progressbarTrackerColor = ProgressbarTrackerIndicatorColorGreen,
            titleColor = ProgressbarIndicatorColorGreenDark,
            scaleType = stringResource(id = R.string.scale_h),
            radius = 100.dp
        )
    )
    HomeScreenMultipleCircularProgressbarCardItem(
        items
    )
}