package com.example.storyapp.data.paging.util

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storyapp.data.response.list.ListStory

@Dao
interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(quote: List<ListStory>)

    @Query("SELECT * FROM list_story")
    fun getAllStory(): PagingSource<Int, ListStory>

    @Query("DELETE FROM list_story")
    suspend fun deleteAll()
}