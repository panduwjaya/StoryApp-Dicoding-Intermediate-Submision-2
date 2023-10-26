package com.example.storyapp.ui.primary.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.storyapp.R
import com.example.storyapp.data.response.list.ListStory
import com.example.storyapp.databinding.ActivityDetailStoryBinding
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class DetailStoryActivity : AppCompatActivity() {

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(this)
    }

    private val viewModel: DetailViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityDetailStoryBinding
    private lateinit var listStory: ListStory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listStory = intent.getParcelableExtra<ListStory>(EXTRA_ID) as ListStory
        getDetail(listStory.id)
    }

    private fun getDetail(dataId: String){
        viewModel.getDetailStory(dataId).observe(this){result->
            when(result) {
                is Result.Loading -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Glide.with(this)
                        .load(result.data.story.photoUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.ivUser)
                    binding.tvNameUser.text = "Nama : ${result.data.story.name}"
                    binding.tvIdUser.text = "Id Pengguna : ${result.data.story.id}"
                    binding.tvDescriptionUser.text = "Deskripsi : ${result.data.story.description}" ?: "Kosong"
                    binding.tvCreatedAt.text = "Created At : ${result.data.story.createdAt}"
                }
                is Result.Error -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Toast.makeText(this, result.error, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}