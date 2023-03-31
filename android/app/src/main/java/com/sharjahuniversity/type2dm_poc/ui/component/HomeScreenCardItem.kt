package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Constants

@Composable
fun HomeScreenCardItems(
    title: String,
    subTitle: String,
    iconId: Int,
    iconBackgroundBrush: Brush,
    shape: RoundedCornerShape = RoundedCornerShapes.medium as RoundedCornerShape,
    onClickAdd: () -> Unit,
    onClickCard: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp)
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true
                ),
                onClick = onClickCard
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = CircleShape
                        )
                        .background(iconBackgroundBrush, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = title,
                        tint = White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        color = HomeScreenCardItemsTitle,
                        fontSize = 15.sp,
                        style = TextStyle(
                            textDirection = TextDirection.ContentOrLtr
                        ),
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(
                        text = subTitle,
                        color = HomeScreenCardItemsSubtitle,
                        fontSize = 13.sp,
                        style = TextStyle(
                            textDirection = TextDirection.ContentOrLtr
                        ),
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
            if (false)TextButton(
                onClick = onClickAdd,
                shape = CircleShape,
                modifier = Modifier.size(28.dp),
                contentPadding = PaddingValues(end = 0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_icon),
                    contentDescription = Constants.AddButton,
                    Modifier.size(14.dp),
                    tint = HomeScreenCardItemsAddButton
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenCardItemPreview() {
    HomeScreenCardItems(
        title = "Title",
        subTitle = "Subtitles",
        iconId = R.drawable.ic_steps,
        iconBackgroundBrush = FoodScreenBrush,
        onClickAdd = {}
    ) {}
}