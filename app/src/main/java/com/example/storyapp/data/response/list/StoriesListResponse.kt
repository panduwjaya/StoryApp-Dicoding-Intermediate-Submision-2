package com.example.storyapp.data.response.list

import com.google.gson.annotations.SerializedName

data class StoriesListResponse(
    @field:SerializedName("item")
    val item: List<StoryItem>
)

data class StoryItem(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("photoUrl")
    val photoUrl: String,
    @field:SerializedName("createdAt")
    val createdAt: String,
    @field:SerializedName("lat")
    val lat: Double,
    @field:SerializedName("lon")
    val lon: Double,
)