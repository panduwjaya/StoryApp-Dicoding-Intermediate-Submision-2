package com.example.storyapp.data.response.list


import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<StoriesListResponse>
)