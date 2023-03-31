package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.CircularProgressbar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.Utils
import java.util.*

@Composable
fun WaterDetailsScreenCircularProgressCard(
    circularProgressbar: CircularProgressbar,
    valueText: String,
    targetText: String,
    shape: RoundedCornerShape = RoundedCornerShapes.medium as RoundedCornerShape
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(White, shape)
            .fillMaxWidth()
            .heightIn(300.dp, 500.dp)
            .padding(20.dp)
    ) {
        val progress = Utils.animateFloatValue(
            value = circularProgressbar.value
        )
        Text(
            text = circularProgressbar.title,
            color = DefaultCardItemsTitleColor,
            fontSize = 16.sp,
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_glass_of_water),
                    contentDescription = Constants.WaterIcon,
                    tint = WaterSectionSkyDark,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = String.format(
                        Locale.ENGLISH,
                        "${progress.toInt()} ${circularProgressbar.scaleType}"
                    ),
                    color = DefaultCardItemsSubtitle,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.height(16.dp)
                )
            }
        }
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            columns = GridCells.Adaptive(24.dp),
            contentPadding = PaddingValues(0.dp),
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            items(circularProgressbar.totalValue.toInt() * 2 - 1) { i: Int ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(32.dp)
                        .wrapContentWidth()
                        .padding(vertical = 4.dp)
                ) {
                    if (i % 2 == 0) Icon(
                        painter = painterResource(id = R.drawable.ic_glass_of_water),
                        contentDescription = Constants.WaterIcon,
                        tint = WaterSectionSkyDark,
                        modifier = Modifier
                            .size(32.dp)
                            .alpha(if (i > circularProgressbar.value.toInt() * 2 - 1) 0.5f else 1f)
                    )
                    else if (i % 2 == 1) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_icon),
                            contentDescription = Constants.AddIcon,
                            tint = WaterCardAddIconColor,
                            modifier = Modifier
                                .size(12.dp)
                                .padding(start = 0.dp)
                        )
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
                .padding(horizontal = 8.dp)
        ) {
            /*Text(
                text = progress.toInt().toString(),
                color = DefaultCardItemsSubtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .padding(end = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.fl_oz_of_your),
                color = DefaultCardItemsSubtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                modifier = Modifier
                    .padding(end = 4.dp)
            )
            Text(
                text = circularProgressbar.totalValue.toInt().toString(),
                color = DefaultCardItemsSubtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .padding(end = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.fl_oz_of_goals),
                color = DefaultCardItemsSubtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight(400)
            )*/
            Text(
                text = String.format(
                    stringResource(id = R.string.default_circular_progress_card_last_text),
                    valueText, targetText
                ),
                color = DefaultCardItemsSubtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }
    }
}

@Preview
@Composable
fun WaterDetailsScreenCircularProgressCardPreview() {
    WaterDetailsScreenCircularProgressCard(
        CircularProgressbar(
            title = "Daily Water",
            value = 3f,
            totalValue = 6f,
            progressbarColorBrush = WaterScreenBrush,
            progressbarTrackerColor = WaterSectionSkyProgressbarTrackerIndicator,
            titleColor = WaterSectionSkyDark,
            scaleType = "Glass",
            radius = 90.dp
        ),
        valueText = stringResource(id = R.string.fl_oz_of_your),
        targetText = stringResource(id = R.string.fl_oz_of_goals)
    )
}