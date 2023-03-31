package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class UserDataUploadResponseDataUser(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("student_id")
    val studentId: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)