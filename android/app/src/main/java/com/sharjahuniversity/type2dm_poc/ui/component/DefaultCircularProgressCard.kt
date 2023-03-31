package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun DefaultCircularProgressCard(
    circularProgressbar: CircularProgressbar,
    valueText: String,
    targetText: String,
    iconId: Int,
    iconTint: Color,
    shape: RoundedCornerShape = RoundedCornerShapes.medium as RoundedCornerShape
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(White, shape)
            .fillMaxWidth()
            .padding(24.dp)
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
                    painter = painterResource(id = iconId),
                    contentDescription = Constants.WaterIcon,
                    tint = iconTint,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = String.format(
                        circularProgressbar.scaleType,
                        progress.toInt().toString()
                    ),
                    color = DefaultCardItemsSubtitle,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.height(16.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
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
                text = valueText,
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
                text = targetText,
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
fun DefaultCircularProgressCardPreview() {
    DefaultCircularProgressCard(
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
        iconId = R.drawable.ic_steps,
        iconTint = WaterSectionSkyDark,
        valueText = stringResource(id = R.string.fl_oz_of_your),
        targetText = stringResource(id = R.string.fl_oz_of_goals)
    )
}