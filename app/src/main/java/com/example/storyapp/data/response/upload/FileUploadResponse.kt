package com.example.storyapp.data.response.upload


import com.google.gson.annotations.SerializedName

data class FileUploadResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)