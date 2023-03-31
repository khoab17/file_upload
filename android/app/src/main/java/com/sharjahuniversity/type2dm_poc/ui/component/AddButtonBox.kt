package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.ui.theme.AddButtonBoxTitleColor
import com.sharjahuniversity.type2dm_poc.ui.theme.FoodScreenBrush
import com.sharjahuniversity.type2dm_poc.ui.theme.RoundedCornerShapes
import com.sharjahuniversity.type2dm_poc.ui.theme.White
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.utils.Constants

@Composable
fun AddButtonBox(
    modifier: Modifier = Modifier,
    text: String,
    buttonBrush: Brush,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()

            .clip(RoundedCornerShapes.medium)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true
                ),
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Box(
                modifier = Modifier
                    .background(
                        brush = buttonBrush,
                        shape = CircleShape,
                        alpha = if (enabled) 1f else .5f
                    )
                    .alpha(if (enabled) 1f else .5f)
                    .padding(4.dp)
                    .size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = Constants.AddButton,
                    tint = White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight(500),
                lineHeight = 23.sp,
                color = AddButtonBoxTitleColor,
                modifier = Modifier
                    .alpha(if (enabled) 1f else .5f)
                    .padding(top = 4.dp, bottom = 14.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddButtonBoxPreview() {
    AddButtonBox(
        text = "Add more foods",
        buttonBrush = FoodScreenBrush,
        enabled = true,
        onClick = {}
    )
}