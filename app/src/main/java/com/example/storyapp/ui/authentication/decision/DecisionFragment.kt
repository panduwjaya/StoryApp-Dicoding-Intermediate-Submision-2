package com.example.storyapp.ui.authentication.decision

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentDecisionBinding

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

        binding.btnLoginFragment.setOnClickListener{
            view.findNavController().navigate(R.id.action_decisionFragment_to_loginFragment)
        }

        binding.btnSignupFragment.setOnClickListener{
            view.findNavController().navigate(R.id.action_decisionFragment_to_SignUpFragment)
        }

        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.tvTitle, View.TRANSLATION_X, -30f, 50f).apply {
            duration = 3000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

}