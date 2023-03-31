package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Constants

@Composable
fun AddItemCard(
    title: String,
    brush: Brush,
    color: Color,
    addButtonClicked: (quantity: Int) -> Unit
) {
    var quantity by remember {
        mutableStateOf(0)
    }
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(White, RoundedCornerShapes.medium)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .heightIn(min = 62.dp, max = Dp.Unspecified)
                .weight(0.5f)
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(62.dp)
                .requiredWidthIn(184.dp)
                .padding(end = 0.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 0.dp)
                ) {
                    TextButton(
                        onClick = { if (quantity > 0) quantity-- },
                        shape = CircleShape,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            //.padding(start = 0.dp, end = 0.dp)
                            .clickable(
                                enabled = quantity > 0,
                                onClick = { if (quantity > 0) quantity-- }
                            ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = Constants.RemoveButton,
                            tint = AddRemoveItemQuantityIconColor,
                            modifier = Modifier
                                .size(14.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 0.dp, end = 0.dp)
                            //.padding(horizontal = 8.dp)
                            .border(1.dp, AddItemQuantityBorderColor, RoundedCornerShapes.small)
                    ) {
                        BasicTextField(
                            value = quantity.toString(),
                            onValueChange = {
                                quantity =
                                    if (it.isNotEmpty() && it.toFloat().toInt() >= 0)
                                        it.toFloat().toInt()
                                    else 0
                            },
                            textStyle = TextStyle(
                                color = AddItemQuantityColor,
                                fontWeight = FontWeight(500),
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .padding(vertical = 6.dp, horizontal = 16.dp)
                        )
                    }
                    TextButton(
                        onClick = { quantity++ },
                        shape = CircleShape,
                        modifier = Modifier
                            //.padding(start = 0.dp, end = 0.dp)
                            .size(28.dp)
                            .clip(CircleShape)
                            .clickable(
                                onClick = { quantity++ }
                            ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_icon),
                            contentDescription = Constants.AddButton,
                            tint = AddRemoveItemQuantityIconColor,
                            modifier = Modifier
                                .size(14.dp)
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 16.dp, bottom = 16.dp, end = 0.dp)
                        .background(
                            brush = if (quantity > 0) brush else WhiteBrush,
                            shape = RoundedCornerShapes.small
                        )
                        .widthIn(min = 56.dp, max = 56.dp)
                        .border(
                            width = 1.dp,
                            color = if (quantity > 0) Color.Transparent else color,
                            shape = RoundedCornerShapes.small
                        )
                        .clip(RoundedCornerShapes.small)
                        .clickable(
                            enabled = quantity > 0,
                            onClick = {
                                addButtonClicked(quantity)
                                quantity = 0
                            }
                        )
                ) {
                    Text(
                        text = stringResource(R.string.add),
                        color = if (quantity > 0) White else color,
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
    }
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
}

@Preview
@Composable
fun AddItemCardPreview() {
    AddItemCard(
        "AppleAppleAppleAppleAppleApple\nAppleAppleAppleAppleAppleApple\nAppleAppleAppleApple\nAppleAppleAppleAppleAppleAppleAppleAppleApple\nAppleAppleAppleAppleApple",
        FoodScreenBrush,
        FoodScreenCyanColor
    ) {}
}