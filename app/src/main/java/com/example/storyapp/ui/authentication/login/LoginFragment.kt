package com.example.storyapp.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.storyapp.R
import com.example.storyapp.data.token.TokenPreference
import com.example.storyapp.data.token.TokenViewModel
import com.example.storyapp.data.token.TokenViewModelFactory
import com.example.storyapp.databinding.FragmentLoginBinding
import com.example.storyapp.ui.custom.EmailEditText
import com.example.storyapp.ui.custom.MyCustomButtonLogin
import com.example.storyapp.ui.custom.PasswordEditText
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result

class LoginFragment : Fragment() {

    private lateinit var myButtonLogin: MyCustomButtonLogin
    private lateinit var emailEditText: EmailEditText
    private lateinit var passwordEditText: PasswordEditText

    private var isEmailTextChanged = false
    private var isPasswordTextChanged = false

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
    private val pref: TokenPreference = TokenPreference.getInstance(requireContext().dataStore)
    private val tokenViewModel: TokenViewModel = ViewModelProvider(requireActivity(), TokenViewModelFactory(pref)).get(
        TokenViewModel::class.java
    )

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(requireActivity())
    }

    private val viewModel: LoginViewModel by viewModels {
        factory
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myButtonLogin = binding.btnLogin
        emailEditText = binding.edLoginEmail
        passwordEditText = binding.edLoginPassword

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

        myButtonLogin.setOnClickListener {
            val emailLogin = emailEditText.text.toString()
            val passwordLogin = passwordEditText.text.toString()
            postLogin(emailLogin,passwordLogin)
        }
    }

    private fun checkBothTextChanged() {
        if (isEmailTextChanged && isPasswordTextChanged) {
            setMyButtonEnable()
        }
    }

    private fun setMyButtonEnable() {
        val emailValue = emailEditText.text
        val passwordValue = passwordEditText.text

        myButtonLogin.isEnabled = emailValue != null && emailValue.toString().isNotEmpty() && passwordValue != null && passwordValue.toString().isNotEmpty()
    }

    private fun postLogin(emailLogin: String,passwordLogin: String) {
        viewModel.postLogin(emailLogin,passwordLogin).observe(viewLifecycleOwner){result->
            when(result) {
                is Result.Loading -> {
                    binding.progressBarLogin.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBarLogin.visibility = View.GONE

                    val token = result.data.loginResult.token
                    tokenViewModel.saveToken(token)
                    Toast.makeText(context, "Login is success", Toast.LENGTH_LONG).show()
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_mainActivity2)
                    requireActivity().finish()
                }
                is Result.Error -> {
                    binding.progressBarLogin.visibility = View.GONE
                    Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
                }

                else -> {
                    Toast.makeText(context, "Something Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}