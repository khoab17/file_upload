package com.sharjahuniversity.type2dm_poc.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.IconDetails
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Constants

@Composable
fun SearchBox(
    hints: String,
    brush: Brush,
    iconId: Int,
    iconColor: Color,
    itemList: List<String>,
    addButtonClicked: (String, Int) -> Unit
) {
    var searchText by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var showHint by remember {
        mutableStateOf(true)
    }
    var title by remember {
        mutableStateOf("")
    }
    var similarList by remember {
        mutableStateOf(listOf<String>())
    }
    Log.d("items", "SearchBox: $itemList")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = White,
                shape = RoundedCornerShapes.medium
            )
            .clip(RoundedCornerShapes.medium)
        //.padding(vertical = 4.dp)
    ) {
        if (showDialog) AddItemDialogWithAddRemoveButton(
            title = title,
            icon = IconDetails("", iconId),
            unit = "unit",
            brush = brush,
            color = iconColor,
            addButtonText = stringResource(R.string.add_food),
            onDismiss = { showDialog = false }
        ) { title, quantity ->
            showDialog = false
            addButtonClicked(title, quantity)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                //horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, AddItemQuantityBorderColor, RoundedCornerShapes.small)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = Constants.Search,
                        tint = LeadingSearchIconColor,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(start = 12.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    )
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (showHint) Text(
                            text = hints,
                            fontWeight = FontWeight(300),
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = SearchBoxHintColor,
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
                        )
                        BasicTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                Log.d("search", "SearchBox: $it")
                                expanded = searchText.isNotEmpty()
                                similarList = itemList.filter { st ->
                                    st.contains(searchText.toRegex(RegexOption.IGNORE_CASE))
                                }
                                Log.d("search", "SearchBox: $expanded")
                                Log.d("search", "SearchBox: $similarList")
                            },
                            textStyle = TextStyle(
                                color = SearchBoxTextColor,
                                fontWeight = FontWeight(300),
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                            ),
                            modifier = Modifier
                                .onFocusChanged {
                                    expanded = it.isFocused && searchText.isNotEmpty()
                                    showHint = !it.isFocused && searchText.isEmpty()
                                }
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 8.dp)
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded && similarList.isNotEmpty(),
                    properties = PopupProperties(focusable = false),
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth(.61f)
                        .background(White, shape = RoundedCornerShapes.small)
                ) {
                    similarList.take(4).forEach {
                        DropdownMenuItem(
                            onClick = {
                                searchText = ""
                                expanded = false
                                showDialog = true
                                title = it
                            }) {
                            Text(text = it)
                        }
                    }
                }
            }
            Box {
                Box(
                    modifier = Modifier
                        .background(
                            brush = brush,
                            shape = RoundedCornerShapes.small
                        )
                        .size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = Constants.Search,
                        tint = White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchBoxPreview() {
    SearchBox(
        hints = "search",
        iconId = R.drawable.ic_food_icon,
        iconColor = FoodScreenCyanColor,
        brush = ProgressbarIndicatorColorCyanBrush,
        itemList = listOf("item1", "item2", "item3", "item4", "item5"),
        addButtonClicked = { _, _ -> }
    )
}