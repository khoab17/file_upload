package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchSuggestionsResponseDataSuggestion(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("users")
    val users: List<Any>
)