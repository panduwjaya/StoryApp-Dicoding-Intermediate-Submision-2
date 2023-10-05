package com.example.storyapp.ui.primary.logout

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
import androidx.navigation.Navigation
import com.example.storyapp.R
import com.example.storyapp.data.token.TokenPreference
import com.example.storyapp.data.token.TokenViewModel
import com.example.storyapp.data.token.TokenViewModelFactory
import com.example.storyapp.databinding.FragmentLogoutBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class LogOutFragment : Fragment() {

    private val pref: TokenPreference = TokenPreference.getInstance(requireContext().dataStore)
    private val tokenViewModel: TokenViewModel = ViewModelProvider(requireActivity(), TokenViewModelFactory(pref)).get(
        TokenViewModel::class.java
    )

    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPersonalName.text
        binding.tvPersonalEmail.text

        binding.actionLogout.setOnClickListener{
            tokenViewModel.removeToken()
            Navigation.createNavigateOnClickListener(R.id.action_logoutFragment_to_authenticationActivity)
        }
    }
}