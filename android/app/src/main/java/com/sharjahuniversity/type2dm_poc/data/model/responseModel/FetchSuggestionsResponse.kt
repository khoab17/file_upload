package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchSuggestionsResponse(
    @SerializedName("data")
    val fetchSuggestionsResponseData: FetchSuggestionsResponseData,
    @SerializedName("success")
    val success: Boolean
)