package com.example.storyapp.ui.primary.list

import android.content.Intent
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
import com.example.storyapp.R
import com.example.storyapp.data.paging.adapter.LoadingStateAdapter
import com.example.storyapp.data.response.list.ListStory
import com.example.storyapp.databinding.FragmentListStoryBinding
import com.example.storyapp.ui.adapter.StoryPagingListAdapter
import com.example.storyapp.ui.primary.detail.DetailStoryActivity
import com.example.storyapp.ui.primary.maps.MapsActivity
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class ListStoryFragment : Fragment() {

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(requireActivity())
    }

    private val viewModel: ListStoryViewModel by viewModels {
        factory
    }

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

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

        binding.fabAddStory.setOnClickListener {
            view.findNavController().navigate(R.id.action_listStoryFragment_to_addStoryFragment)
        }

        binding.fabMaps.setOnClickListener {
            val intent = Intent(requireActivity(), MapsActivity::class.java)
            startActivity(intent)
        }

        getListStory()
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
        val storyAdapter = StoryPagingListAdapter { story ->
            val intent = Intent(activity, DetailStoryActivity::class.java)
            intent.putExtra(DetailStoryActivity.EXTRA_ID, story)
            startActivity(intent)
        }
        binding.rvStory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStory.setHasFixedSize(true)
        binding.rvStory.adapter = storyAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                storyAdapter.retry()
            }
        )
        viewModel.getListStory.observe(viewLifecycleOwner) {
            storyAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }
}