package com.example.storyapp.ui.primary.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernew.data.local.StoryEntity
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentListStoryBinding
import com.example.storyapp.ui.adapter.StoryListAdapter
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class ListStoryFragment : Fragment() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(requireActivity())
    }

    private val viewModel: ListStoryViewModel by viewModels {
        factory
    }

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<StoryEntity>()
    private val listStoryAdapter = StoryListAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getListStory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        showRecycler()
        listStoryAdapter.setOnItemClickCallback(object : StoryListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: StoryEntity) {
                val mBundle = Bundle()
                mBundle.putString(EXTRA_ID, data.id)
                view.findNavController().navigate(R.id.action_listStoryFragment_to_detailStoryFragment, mBundle)
            }
        })

        binding.fabAddStory.setOnClickListener {
            view.findNavController().navigate(R.id.action_listStoryFragment_to_addStoryFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_menu -> {
                findNavController().navigate(R.id.action_listStoryFragment_to_settingsFragment)
                true
            }

            R.id.profile_menu -> {
                findNavController().navigate(R.id.action_listStoryFragment_to_logoutFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListStory() {
        viewModel.getListStory().observe(viewLifecycleOwner){result->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val storyData = result.data
                    listStoryAdapter.setListUser(storyData)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showRecycler() {
        binding.rvStory.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvStory.setHasFixedSize(true)
        binding.rvStory.adapter = listStoryAdapter
    }
}