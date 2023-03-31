package com.sharjahuniversity.type2dm_poc.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FetchedUserProfileDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "student_id") val studentId: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "age") val age: Double,
    @ColumnInfo(name = "education_level") val educationLevel: String,
    @ColumnInfo(name = "income") val income: Double,
    @ColumnInfo(name = "occupation") val occupation: String,
    @ColumnInfo(name = "organization") val organization: String

)