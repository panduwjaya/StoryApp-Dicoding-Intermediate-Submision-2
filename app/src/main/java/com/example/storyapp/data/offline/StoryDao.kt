package com.example.storyapp.data.offline

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StoryDao {

    // input data kedalam database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStory(user: List<StoryEntity>)

    // mengambil data
    @Query("SELECT * FROM favorite_user")
    fun getStory(): LiveData<List<StoryEntity>>

    // memperbarui data
    @Update
    fun updateStory(user: StoryEntity)

    @Query("DELETE FROM favorite_user")
    fun deleteAll()
}