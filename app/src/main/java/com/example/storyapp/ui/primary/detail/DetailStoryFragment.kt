package com.example.storyapp.ui.primary.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentDetailStoryBinding
import com.example.storyapp.ui.primary.list.ListStoryFragment
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class DetailStoryFragment : Fragment() {

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(requireActivity())
    }

    private val viewModel: DetailViewModel by viewModels {
        factory
    }

    private var _binding: FragmentDetailStoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataId = arguments?.getString(ListStoryFragment.EXTRA_ID)
        getDetail(dataId!!)

    }

    private fun getDetail(dataId: String){
        viewModel.getDetailStory(dataId).observe(viewLifecycleOwner){result->
            when(result) {
                is Result.Loading -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Glide.with(requireActivity())
                        .load(result.data.photoUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.ivUser)
                    binding.tvNameUser.text = result.data.name
                    binding.tvDescriptionUser.text = result.data.description
                }
                is Result.Error -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}