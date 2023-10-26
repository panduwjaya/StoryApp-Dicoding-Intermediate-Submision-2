package com.example.storyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.storyapp.data.response.list.ListStory
import com.example.storyapp.databinding.ItemRowLayoutBinding

class StoryPagingListAdapter(private val onItemClick: (ListStory) -> Unit) :
    PagingDataAdapter<ListStory, StoryPagingListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding,onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemRowLayoutBinding, val onItemClick: (ListStory) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListStory) {
            Glide.with(itemView.context)
                .load(data.photoUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivPhotoUrl)
            binding.tvName.text = data.name
            binding.tvDesc.text = data.description
            binding.tvCreatedAt.text = data.createdAt

            itemView.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStory>() {
            override fun areItemsTheSame(oldItem: ListStory, newItem: ListStory): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListStory, newItem: ListStory): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}