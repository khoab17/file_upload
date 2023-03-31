package com.sharjahuniversity.type2dm_poc.data.model.responseModel

data class Project(
    val _id: String,
    val active: Boolean,
    val createdAt: String,
    val location: String,
    val name: String,
    val participation_count: Int,
    val rating: Int,
    val survey: List<Survey>,
    val type: String,
    val updatedAt: String
)