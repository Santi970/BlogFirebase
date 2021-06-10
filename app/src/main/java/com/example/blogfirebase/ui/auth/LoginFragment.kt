package com.example.blogfirebase.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.blogfirebase.R
import com.example.blogfirebase.core.Resource
import com.example.blogfirebase.data.remote.auth.LoginDataSource
import com.example.blogfirebase.databinding.FragmentLoginBinding
import com.example.blogfirebase.domain.auth.LoginRepoImpl
import com.example.blogfirebase.presentation.auth.LoginScreenViewModel
import com.example.blogfirebase.presentation.auth.LoginScreenViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<LoginScreenViewModel>{
        LoginScreenViewModelFactory(LoginRepoImpl(LoginDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        usUserLoggedIn()
        doLogin()
        goToSignUpPage()
    }

    private fun usUserLoggedIn(){
        firebaseAuth.currentUser?.let {
                findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)


            }
    }

    private fun doLogin(){
        binding.btnSignin.setOnClickListener{
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPasword.text.toString().trim()
            validateCredentials(email, password)
            singIn(email,password)
        }
    }

    private fun goToSignUpPage(){
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun validateCredentials(email: String, password: String){
        if(email.isEmpty()){
            binding.editTextEmail.error = "E-mail is empty"
            return
        }

        if(password.isEmpty()){
            binding.editTextPasword.error = "Password is empty"
            return
        }
    }


    private fun singIn(email: String, password: String){
            viewModel.signIn(email, password).observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.btnSignin.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                        Toast.makeText(requireContext(), "Welcome: ${result.data?.email}", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnSignin.isEnabled = true
                        Toast.makeText(requireContext(), "Error: ${result.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}