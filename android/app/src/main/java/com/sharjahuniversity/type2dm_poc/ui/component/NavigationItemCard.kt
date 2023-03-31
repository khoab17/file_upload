package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.FoodScreenBrush
import com.sharjahuniversity.type2dm_poc.ui.theme.FoodSectionIconBorderColor
import com.sharjahuniversity.type2dm_poc.ui.theme.RoundedCornerShapes
import com.sharjahuniversity.type2dm_poc.ui.theme.White

@Composable
fun NavigationItemCard(
    title: String,
    iconId: Int,
    iconBackgroundBrush: Brush,
    iconBorderColor: Color,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = White,
        shape = RoundedCornerShapes.medium,
        modifier = Modifier
            .clip(RoundedCornerShapes.medium)
            .clickable(
                onClick = onClick,
            )
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(White, RoundedCornerShapes.medium)
                .padding(horizontal = 28.dp, vertical = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    //.padding(vertical = 22.dp)
                    .wrapContentSize()
                    .border(0.dp, White, CircleShape)
                    .background(iconBackgroundBrush, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = title,
                    tint = White,
                    modifier = Modifier
                        .size(72.dp)
                        .padding(16.dp)
                )
                Canvas(
                    modifier = Modifier
                        .size(70.dp)
                ) {
                    drawArc(
                        color = iconBorderColor,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(
                            20.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                lineHeight = 21.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Preview
@Composable
fun NavigationItemCardPreview() {
    NavigationItemCard(
        stringResource(id = R.string.add_food),
        R.drawable.ic_food_icon,
        FoodScreenBrush,
        FoodSectionIconBorderColor
    ) {}
}