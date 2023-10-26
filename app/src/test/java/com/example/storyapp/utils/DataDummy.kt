package com.example.storyapp.utils

import com.example.storyapp.data.response.list.ListStory

object DataDummy {

    fun generateDummyStoryResponse(): List<ListStory> {
        val items: MutableList<ListStory> = arrayListOf()
        for (i in 0..100) {
            val quote = ListStory(
                i.toString(),
                "name author $i",
                "quote $i",
                "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/commons/feature-1-kurikulum-global-3.png",
                "created at $i",
                0.1 + i,
                0.1 + i

            )
            items.add(quote)
        }
        return items
    }
}
