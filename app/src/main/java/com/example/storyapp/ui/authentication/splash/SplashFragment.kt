package com.example.storyapp.ui.authentication.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.storyapp.R
import com.example.storyapp.data.token.TokenPreference
import com.example.storyapp.data.token.TokenViewModel
import com.example.storyapp.data.token.TokenViewModelFactory
import com.example.storyapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.observeOn

class SplashFragment : Fragment() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
    private val pref: TokenPreference = TokenPreference.getInstance(requireContext().dataStore)
    private val tokenViewModel: TokenViewModel = ViewModelProvider(requireActivity(), TokenViewModelFactory(pref)).get(
        TokenViewModel::class.java
    )

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var token: String? = null
        tokenViewModel.readToken().observe(viewLifecycleOwner){result->
            token = result
        }

        lifecycleScope.launchWhenResumed {
            delay(2000)
            if(token == null){
                findNavController().navigate(R.id.action_splashFragment_to_decisionFragment)
            }else{
                // splash to mainActivity
                findNavController().navigate(R.id.action_splashFragment_to_mainActivity2)
                requireActivity().finish()
            }
        }

    }
}