package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchUserResponseDataUser(

    @SerializedName("_id")
    val id: String,

    val name: String,
    val email: String,

    @SerializedName("image_url")
    val imageURL: String,

    val createdAt: String,
    val updatedAt: String,

    @SerializedName("student_id")
    val studentID: String,

    @SerializedName("fcm_registration_token")
    val fcmRegistrationToken: String,

    val age: Double,

    @SerializedName("education_level")
    val educationLevel: String,

    val gender: String,
    val income: Double,
    val occupation: String,
    val phone: String,
    val surname: String,
    val organization: String,
    val status: String
)