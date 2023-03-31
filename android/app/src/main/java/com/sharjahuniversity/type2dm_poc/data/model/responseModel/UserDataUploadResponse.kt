package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class UserDataUploadResponse(
    @SerializedName("data")
    val userDataUploadResponseData: UserDataUploadResponseData,
    @SerializedName("success")
    val success: Boolean
)