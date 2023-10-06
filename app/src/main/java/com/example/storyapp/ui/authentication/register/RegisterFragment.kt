package com.example.storyapp.ui.authentication.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentRegisterBinding
import com.example.storyapp.ui.custom.EmailEditText
import com.example.storyapp.ui.custom.MyCustomButtonRegister
import com.example.storyapp.ui.custom.NameEditText
import com.example.storyapp.ui.custom.PasswordEditText
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class RegisterFragment : Fragment() {

    private lateinit var myButtonRegister: MyCustomButtonRegister
    private lateinit var nameEditText: NameEditText
    private lateinit var emailEditText: EmailEditText
    private lateinit var passwordEditText: PasswordEditText

    private var isNameTextChanged = false
    private var isEmailTextChanged = false
    private var isPasswordTextChanged = false

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

        myButtonRegister = binding.btnSignup
        nameEditText = binding.edRegisterName
        emailEditText = binding.edRegisterEmail
        passwordEditText = binding.edRegisterPassword

        nameEditText.addTextChangedListener(object : TextWatcher {
            // dipanggil ketika text belum dirubah
            // alasan method ini kosong dikarenakan tidak ada efek yg ditampilkan sebelum text dirubah
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            // dipanggil ketika text sedang mengalami perubahan
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isNameTextChanged = true
                checkBothTextChanged()
            }
            // dipanggil ketika text sudah dirubah
            override fun afterTextChanged(s: Editable) {
            }
        })

        emailEditText.addTextChangedListener(object : TextWatcher {
            // dipanggil ketika text belum dirubah
            // alasan method ini kosong dikarenakan tidak ada efek yg ditampilkan sebelum text dirubah
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            // dipanggil ketika text sedang mengalami perubahan
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isEmailTextChanged = true
                checkBothTextChanged()
            }
            // dipanggil ketika text sudah dirubah
            override fun afterTextChanged(s: Editable) {
            }
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            // dipanggil ketika text belum dirubah
            // alasan method ini kosong dikarenakan tidak ada efek yg ditampilkan sebelum text dirubah
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            // dipanggil ketika text sedang mengalami perubahan
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isPasswordTextChanged = true
                checkBothTextChanged()
            }
            // dipanggil ketika text sudah dirubah
            override fun afterTextChanged(s: Editable) {
            }
        })

        myButtonRegister.setOnClickListener {
            val nameRegister = nameEditText.text.toString()
            val emailRegister = emailEditText.text.toString()
            val passwordRegister = passwordEditText.text.toString()
            postRegister(nameRegister,emailRegister,passwordRegister)
        }

        val nameRegister = binding.edRegisterName.text.toString()
        val emailRegister = binding.edRegisterEmail.text.toString()
        val passwordRegister = binding.edRegisterPassword.text.toString()
        postRegister(nameRegister,emailRegister,passwordRegister)
    }

    private fun checkBothTextChanged() {
        if (isNameTextChanged && isEmailTextChanged && isPasswordTextChanged) {
            setMyButtonEnable()
        }
    }

    private fun setMyButtonEnable() {
        val nameValue = nameEditText.text
        val emailValue = emailEditText.text
        val passwordValue = passwordEditText.text

        myButtonRegister.isEnabled = nameValue != null && nameValue.toString().isNotEmpty() && emailValue != null && emailValue.toString().isNotEmpty() && passwordValue != null && passwordValue.toString().isNotEmpty()
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