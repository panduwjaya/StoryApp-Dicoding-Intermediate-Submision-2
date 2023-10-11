package com.example.storyapp.data.offline

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_user")
@Parcelize
data class StoryEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "id")
    val id: String,

    @field:ColumnInfo(name = "name")
    val name: String? = null,
    @field:ColumnInfo(name = "description")
    val description: String? = null,
    @field:ColumnInfo(name = "photoUrl")
    val photoUrl: String? = null,
    @field:ColumnInfo(name = "createdAt")
    var createdAt: String? = null,
    @field:ColumnInfo(name = "lat")
    var lat: Double? = null,
    @field:ColumnInfo(name = "lon")
    var lon: Double? = null
) : Parcelable