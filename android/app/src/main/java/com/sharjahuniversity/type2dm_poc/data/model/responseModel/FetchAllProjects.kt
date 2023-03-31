package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchAllProjects(
    val count: Int,
    @SerializedName("data")
    val dataProjectList: DataProjectList,
    val success: Boolean
)