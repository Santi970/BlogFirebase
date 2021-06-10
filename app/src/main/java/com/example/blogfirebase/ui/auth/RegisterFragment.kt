package com.example.blogfirebase.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.blogfirebase.R
import com.example.blogfirebase.core.Result
import com.example.blogfirebase.data.remote.auth.AuthDataSource
import com.example.blogfirebase.databinding.FragmentRegisterBinding
import com.example.blogfirebase.domain.auth.AuthRepoImpl
import com.example.blogfirebase.presentation.auth.AuthViewModel
import com.example.blogfirebase.presentation.auth.AuthViewModelFactory
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel>{
        AuthViewModelFactory(AuthRepoImpl(AuthDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        signUp()
    }


    private fun signUp(){

        binding.btnSignUp.setOnClickListener {

            val username = binding.editTextUsername.text.toString().trim()
            val email = binding.editTextEmailSignUp.text.toString().trim()
            val password = binding.editTextPasswordSignUp.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPass.text.toString().trim()


            if (validateUserData(password, confirmPassword, username, email)) return@setOnClickListener

            createUser(username,email, password)
        }

    }

    private fun createUser(username: String, email: String, password: String) {
        viewModel.signUp(email, password,username).observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Result.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignUp.isEnabled = false
                }
                is Result.Success ->{
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_homeScreenFragment)

                }
                is Result.Failure->{
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignUp.isEnabled = true
                    Toast.makeText(requireContext(), "Error: ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun validateUserData(
        password: String,
        confirmPassword: String,
        username: String,
        email: String
    ): Boolean {
        if (password != confirmPassword) {

            binding.editTextConfirmPass.error = "The passwords are not the same"
            binding.editTextPasswordSignUp.error = "The passwords are not the same"
            return true
        }

        if (username.isEmpty()) {
            binding.editTextUsername.error = "Username is empty"
            return true
        }

        if (email.isEmpty()) {
            binding.editTextEmailSignUp.error = "Email is empty"
            return true
        }

        if (password.isEmpty()) {
            binding.editTextPasswordSignUp.error = "Password is empty"
            return true
        }

        if (confirmPassword.isEmpty()) {
            binding.textInputConfirmPass.error = "Password is empty"
            return true
        }
        return false
    }
}
