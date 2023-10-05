package com.example.storyapp.ui.authentication.decision

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentDecisionBinding
import com.example.storyapp.databinding.FragmentLogoutBinding

class DecisionFragment : Fragment() {

    private var _binding: FragmentDecisionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDecisionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginFragment.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_decisionFragment_to_loginFragment)
        )

        binding.btnSignupFragment.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_decisionFragment_to_SignUpFragment)
        )
    }



}