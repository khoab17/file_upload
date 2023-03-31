package com.sharjahuniversity.type2dm_poc.data.model

data class UserProfileData(
    val name: String,
    val email: String,
    val phone: String,
    val surname: String,
    val age: Double,
    val education_level: String,
    val income: Double,
    val occupation: String,
    val organization: String,
    val gender: String,
    val status: String
    //val hasType2dm: Boolean,
    //val specialNote: String
    //val activityGoal: String,
    //val weightGoal: String
)