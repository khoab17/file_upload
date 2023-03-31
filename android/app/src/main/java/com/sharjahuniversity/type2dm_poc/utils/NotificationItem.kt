package com.sharjahuniversity.type2dm_poc.utils

sealed class NotificationItem(
    val id: Int,
    val channelID: String,
    val channelName: String
) {
    object NewSuggestion : NotificationItem(
        0, "0", "NEW_SUGGESTION"
    )
    object NewSurvey : NotificationItem(
        1, "1", "NEW_SURVEY"
    )
    object NewGoalsSet : NotificationItem(
        2, "2", "NEW_GOALS_SET"
    )
    object Others : NotificationItem(
        3, "3", "OTHERS"
    )
}