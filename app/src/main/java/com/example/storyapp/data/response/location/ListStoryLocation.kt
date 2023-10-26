package com.example.storyapp.data.response.location

import com.google.gson.annotations.SerializedName

class ListStoryLocation(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,
    @field:SerializedName("createdAt")
    val createdAt: String? = null,
    @field:SerializedName("lat")
    val lat: Double,
    @field:SerializedName("lon")
    val lon: Double,
)