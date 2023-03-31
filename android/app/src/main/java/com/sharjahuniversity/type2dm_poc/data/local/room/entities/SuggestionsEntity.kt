package com.sharjahuniversity.type2dm_poc.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuggestionsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "date_time") val dateTime: String,
    @ColumnInfo(name = "heading") val heading: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "is_read") val isRead: Boolean,
    @ColumnInfo(name = "is_bookmarked") val isBookmarked: Boolean
)