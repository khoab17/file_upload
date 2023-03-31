package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchFoodsResponseDataFood(
    @SerializedName("calories_per_serving")
    val caloriesPerServing: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("default_serving_quantity")
    val defaultServingQuantity: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("macros")
    val macros: FetchFoodsResponseDataFoodMacros,
    @SerializedName("name")
    val name: String,
    @SerializedName("serving_unit")
    val servingUnit: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("icon")
    val icon: FetchFoodsResponseDataFoodIcon
)