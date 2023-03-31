package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.IconDetails
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.Utils

@Composable
fun AddItemDialogForIntValue(
    title: String,
    icon: IconDetails,
    unit: String,
    brush: Brush,
    color: Color,
    addButtonText: String,
    onDismiss: () -> Unit,
    addButtonClicked: (String, Int) -> Unit,
) {
    var quantity by remember {
        mutableStateOf(0)
    }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(BackgroundColor, RoundedCornerShapes.medium)
                .clip(RoundedCornerShapes.medium)
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 38.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.Transparent, RoundedCornerShapes.medium)
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = Constants.CrossButton,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(14.dp)
                        .clickable(
                            enabled = true,
                            onClick = onDismiss
                        )
                )
            }
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
                        .padding(vertical = 8.dp)
                        .weight(0.5f)
                        .heightIn(min = 62.dp, max = Dp.Unspecified)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (icon.url.isEmpty() || icon.url.isBlank() || icon.url.isNullOrEmpty()) {
                            Icon(
                                painter = painterResource(id = icon.iconId),
                                contentDescription = title,
                                tint = color,
                                modifier = Modifier
                                    .size(64.dp)
                                    .wrapContentSize()
                            )
                        } else {
                            Icon(
                                painter = Utils.loadSVGImage(icon = icon),
                                contentDescription = title,
                                tint = color,
                                modifier = Modifier
                                    .size(64.dp)
                                    .wrapContentSize()
                            )
                        }
                        Text(
                            text = title,
                            color = AddItemTitleColor,
                            fontWeight = FontWeight(400),
                            lineHeight = 18.sp,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(62.dp)
                        .padding(end = 12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                                .border(1.dp, AddItemQuantityBorderColor, RoundedCornerShapes.small)
                        ) {
                            BasicTextField(
                                value = quantity.toString(),
                                onValueChange = {
                                    if (it.isNotEmpty() && it.toInt() >= 0)
                                        quantity = it.toInt()
                                    else if (it.isEmpty()) quantity = 0
                                },
                                textStyle = TextStyle(
                                    color = AddItemQuantityColor,
                                    fontWeight = FontWeight(500),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    textAlign = TextAlign.Center,
                                    textDirection = TextDirection.ContentOrLtr
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier
                                    .padding(vertical = 6.dp, horizontal = 0.dp)
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(White, RoundedCornerShapes.medium)
                    .fillMaxWidth()

            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            brush = brush,
                            shape = AddItemDialogButtonRoundedCornerShape,
                            alpha = if (quantity > 0) 1f else 0.5f
                        )
                        .fillMaxWidth()
                        .clip(RoundedCornerShapes.small)
                        .clickable(
                            enabled = quantity > 0,
                            onClick = {
                                addButtonClicked(title, quantity)
                                quantity = 0
                            }
                        )
                ) {
                    Text(
                        text = addButtonText,
                        color = White,
                        fontWeight = FontWeight(600),
                        lineHeight = 14.sp,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AddItemDialogForFloatValue(
    title: String,
    unit: String,
    icon: IconDetails,
    brush: Brush,
    color: Color,
    addButtonText: String,
    onDismiss: () -> Unit,
    addButtonClicked: (String, Float) -> Unit,
) {
    var quantity by remember {
        mutableStateOf(0f)
    }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(BackgroundColor, RoundedCornerShapes.medium)
                .clip(RoundedCornerShapes.medium)
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 38.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.Transparent, RoundedCornerShapes.medium)
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = Constants.CrossButton,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(14.dp)
                        .clickable(
                            enabled = true,
                            onClick = onDismiss
                        )
                )
            }
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
                        .height(62.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (icon.url.isEmpty() || icon.url.isBlank() || icon.url.isNullOrEmpty()) {
                            Icon(
                                painter = painterResource(id = icon.iconId),
                                contentDescription = title,
                                tint = color,
                                modifier = Modifier
                                    .size(64.dp)
                                    .wrapContentSize()
                            )
                        } else {
                            Icon(
                                painter = Utils.loadSVGImage(icon = icon),
                                contentDescription = title,
                                tint = color,
                                modifier = Modifier
                                    .size(64.dp)
                                    .wrapContentSize()
                            )
                        }
                        Text(
                            text = title,
                            color = AddItemTitleColor,
                            fontWeight = FontWeight(400),
                            lineHeight = 14.sp,
                            fontSize = 14.sp,
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(62.dp)
                        .padding(end = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                                .border(1.dp, AddItemQuantityBorderColor, RoundedCornerShapes.small)
                        ) {
                            BasicTextField(
                                value = String.format(
                                    Constants.AddItemDialogTextFieldString,
                                    quantity.toString()
                                ),
                                onValueChange = {
                                    val string = it.replace(",", ".")
                                    quantity = if (it.isEmpty()) {
                                        0f
                                    } else {
                                        when (it.toFloatOrNull()) {
                                            null -> {
                                                0f
                                            }
                                            else -> string.toFloat()
                                        }
                                    }
                                },
                                textStyle = TextStyle(
                                    color = AddItemQuantityColor,
                                    fontWeight = FontWeight(500),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    textAlign = TextAlign.Center,
                                    textDirection = TextDirection.ContentOrLtr
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier
                                    .padding(vertical = 6.dp, horizontal = 0.dp)
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(White, RoundedCornerShapes.medium)
                    .fillMaxWidth()

            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            brush = brush,
                            shape = AddItemDialogButtonRoundedCornerShape,
                            alpha = if (quantity > 0) 1f else 0.5f
                        )
                        .fillMaxWidth()
                        .clip(RoundedCornerShapes.small)
                        .clickable(
                            enabled = quantity > 0,
                            onClick = {
                                addButtonClicked(title, quantity)
                                quantity = 0f
                            }
                        )
                ) {
                    Text(
                        text = addButtonText,
                        color = White,
                        fontWeight = FontWeight(600),
                        lineHeight = 14.sp,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddItemDialogWithoutAddRemoveButtonPreview() {
    AddItemDialogForIntValue(
        title = "itemitemitemitemitemitemitem\nitemitem",
        unit = "unit",
        icon = IconDetails("", R.drawable.ic_food_icon),
        brush = FoodScreenBrush,
        color = FoodScreenCyanColor,
        addButtonText = "Add Food",
        onDismiss = {}
    ) { _, _ -> }
}