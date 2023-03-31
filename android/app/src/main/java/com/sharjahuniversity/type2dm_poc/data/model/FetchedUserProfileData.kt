package com.sharjahuniversity.type2dm_poc.data.model

data class FetchedUserProfileData(
    val email: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val phone: String,
    val studentId: String,
    val surname: String,
    val age: Double,
    val education_level: String,
    val income: Double,
    val occupation: String,
    val organization: String,
    val gender: String
)