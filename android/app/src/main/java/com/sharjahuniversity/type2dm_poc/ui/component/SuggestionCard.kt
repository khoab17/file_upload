package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.Utils

@Composable
fun SuggestionsCard(
    modifier: Modifier = Modifier,
    headingText: String,
    descriptionText: String,
    timeText: String,
    isBookmarked: Boolean,
    bookmarkClicked: (Boolean) -> Unit,
    isRead: Boolean,
    isReadClicked: (Boolean) -> Unit,
    deleteClick: () -> Unit,
    deleteAllClick: () -> Unit,
    cardClick: () -> Unit
) {
    var isBookmarked by mutableStateOf(isBookmarked)

    var isRead by mutableStateOf(isRead)

    var isShowMenu by mutableStateOf(false)

    var isCardClicked by remember{
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShapes.medium)
            .clip(RoundedCornerShapes.medium)
            .clickable {
                isRead = true
                isReadClicked(isRead)
                isCardClicked = !isCardClicked
            }
            .animateContentSize(
                tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 4.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = Utils.getTimeDifferenceInSMHDM(timeText),
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    lineHeight = 19.sp,
                    color = DefaultCardItemsSubtitle
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = if (isBookmarked) painterResource(id = R.drawable.ic_bookmark_selected)
                        else painterResource(id = R.drawable.ic_bookmark_unselected),
                        alpha = if (isBookmarked) 1f else 0.4f,
                        contentDescription = stringResource(R.string.bookmark_icon),
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .padding(start = 0.dp, top = 0.dp, end = 12.dp, bottom = 0.dp)
                            .clickable {
                                isBookmarked = !isBookmarked
                                bookmarkClicked(isBookmarked)
                            }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_charm_menu_meatball),
                        contentDescription = stringResource(R.string.menu_icon),
                        modifier = Modifier
                            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                            .clickable {
                                isShowMenu = !isShowMenu
                            }
                    )
                    MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShapes.medium)) {
                        DropdownMenu(
                            expanded = isShowMenu,
                            onDismissRequest = { isShowMenu = false },
                        ) {
                            if (isRead) DropdownMenuItem(
                                onClick = {
                                    isRead = false
                                    isReadClicked(isRead)
                                    isShowMenu = false
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.mark_as_unread),
                                    fontWeight = FontWeight(400),
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = DefaultTitleColor
                                )
                            }
                            if (!isRead) DropdownMenuItem(
                                onClick = {
                                    isRead = true
                                    isReadClicked(isRead)
                                    isShowMenu = false
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.mark_as_read),
                                    fontWeight = FontWeight(400),
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = DefaultTitleColor
                                )
                            }
                            Divider(
                                color = DefaultDividerColor,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                            DropdownMenuItem(
                                onClick = {
                                    isShowMenu = false
                                    deleteClick()
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.delete_suggestion),
                                    fontWeight = FontWeight(400),
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = DefaultTitleColor
                                )
                            }
                            Divider(
                                color = DefaultDividerColor,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                            DropdownMenuItem(
                                onClick = {
                                    isShowMenu = false
                                    deleteAllClick()
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.delete_all_suggestions),
                                    fontWeight = FontWeight(400),
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = DefaultTitleColor
                                )
                            }
                        }
                    }
                }
            }
        }
        if (headingText != "")Text(
            text = headingText,
            fontWeight = FontWeight(500),
            fontSize = 14.sp,
            lineHeight = 22.sp,
            maxLines = if (!isCardClicked) 1 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .alpha(if (isRead) 0.5f else 1f)
                .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 4.dp)
        )
        Text(
            text = descriptionText,
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            lineHeight = 18.sp,
            maxLines = if (!isCardClicked) 2 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .alpha(if (isRead) 0.5f else 1f)
        )
    }
}

@Preview
@Composable
fun SuggestionsCardPrev() {
    SuggestionsCard(
        headingText = "Health Survey Questions or questionnaires comprise a set of questions that enables you to collect Patient Feedback and Patient Data based on their care and treatment experience.",
        descriptionText = "Health Survey Questions or questionnaires comprise a set of questions that enables you to collect Patient Feedback and Patient Data based on their care and treatment experience. Patient Surveys and Questionnaires comprises evaluation-based questions that enable the hospitals to capture data and to understand the overall health of the patients and other risk factors that lead to a decline in patient satisfaction.",
        timeText = "2022-08-03 03:30:30 PM",
        isBookmarked = true,
        bookmarkClicked = { /*TODO*/ },
        isRead = true,
        isReadClicked = { /*TODO*/ },
        deleteClick = { /*TODO*/ },
        deleteAllClick = { /*TODO*/ }) {
    }
}