package com.example.storyapp.data.response.location

import com.google.gson.annotations.SerializedName

class StoryLocationResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<ListStoryLocation>
)