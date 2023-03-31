package com.sharjahuniversity.type2dm_poc.data.model

data class SuggestionsItem(
    val id: String,
    val dateTime: String,
    val heading: String,
    val description: String,
    val isRead: Boolean,
    val isBookmarked: Boolean
)