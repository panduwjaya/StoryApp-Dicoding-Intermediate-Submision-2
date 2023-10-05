package com.example.storyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.storyapp.data.response.list.StoriesListResponse
import com.example.storyapp.databinding.ItemRowLayoutBinding
import com.example.storyapp.utils.StoryDiffCallback

class StoryListAdapter(private val list: ArrayList<StoriesListResponse>): RecyclerView.Adapter<StoryListAdapter.UserViewHolder>(){

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListUser(listNotes: List<StoriesListResponse>) {
        val diffCallback = StoryDiffCallback(this.list, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list.clear()
        this.list.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserViewHolder(val binding: ItemRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: StoriesListResponse) {

            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(user)
            }

            Glide.with(itemView)
                .load(user.photoUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivPhotoUrl)

            binding.tvName.text = user.name
            binding.tvDesc.text = user.description
            binding.tvCreatedAt.text = user.createdAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryListAdapter.UserViewHolder {
        val binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryListAdapter.UserViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: StoriesListResponse)
    }

}