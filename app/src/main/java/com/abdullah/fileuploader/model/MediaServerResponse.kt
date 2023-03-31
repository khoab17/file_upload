package com.abdullah.fileuploader.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MediaServerResponse(
    @field:SerializedName("file_name")
    val fileName: String,
    @field:SerializedName("original_size")
    val originalSize: Int,
    @field:SerializedName("kraked_size")
    val krakedSize: Int,
    @field:SerializedName("saved_bytes")
    val savedBytes: Int,
    @field:SerializedName("kraked_url")
    val krakedUrl: String,
    @field:SerializedName("original_width")
    val originalWidth: Int,
    @field:SerializedName("original_height")
    val originalHeight: Int,
    @field:SerializedName("kraked_width")
    val krakedWidth: String,
    @field:SerializedName("kraked_height")
    val krakedHeight: String,
    @field:SerializedName("success")
    val success: Boolean
)
