package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchSuggestionsResponseData(
    @SerializedName("count")
    val count: Int,
    @SerializedName("suggestions")
    val fetchSuggestionsResponseDataSuggestions: List<FetchSuggestionsResponseDataSuggestion>
)