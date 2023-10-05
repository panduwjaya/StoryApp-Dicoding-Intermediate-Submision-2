package com.example.storyapp.ui.authentication.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentRegisterBinding
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class RegisterFragment : Fragment() {

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(requireActivity())
    }

    private val viewModel: RegisterViewModel by viewModels {
        factory
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameRegister = binding.edRegisterName.text.toString()
        val emailRegister = binding.edRegisterEmail.text.toString()
        val passwordRegister = binding.edRegisterPassword.text.toString()
        postRegister(nameRegister,emailRegister,passwordRegister)
    }

    private fun postRegister(name: String,email: String,password: String){
        viewModel.postRegister(name,email,password).observe(viewLifecycleOwner){result->
            when(result) {
                is Result.Loading -> {
                    binding.progressBarRegister.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBarRegister.visibility = View.GONE
                    Toast.makeText(context, "message is :$result", Toast.LENGTH_LONG).show()
                    view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment2)
                }
                is Result.Error -> {
                    binding.progressBarRegister.visibility = View.GONE
                    Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}