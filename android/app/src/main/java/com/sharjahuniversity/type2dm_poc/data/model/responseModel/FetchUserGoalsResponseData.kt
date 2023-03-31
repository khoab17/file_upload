package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchUserGoalsResponseData(
    @SerializedName("user")
    val fetchUserGoalsResponseDataUser: FetchUserGoalsResponseDataUser
)