package com.example.storyapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.githubusernew.data.local.StoryEntity
import com.example.storyapp.data.response.list.StoriesListResponse
import com.example.storyapp.data.response.list.StoryItem

/**
 * NoteDiffCallback untuk memeriksa perubahan yang ada pada listNotes
 * Jadi jika ada perubahan pada listNotes, maka akan memperbarui secara otomatis.
 * NoteDiffCallback digunakan sebagai pengganti notifyDataSetChanged
 * yang fungsinya sama-sama untuk melakukan pembaharuan item pada RecyclerView.
 */
class StoryDiffCallback(private val oldNoteList: List<StoryEntity>, private val newNoteList: List<StoryEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNoteList.size
    override fun getNewListSize(): Int = newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldNoteList[oldItemPosition]
        val newNote = newNoteList[newItemPosition]
        return oldNote.name == newNote.name && oldNote.description == newNote.description
    }
}