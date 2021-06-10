package com.example.blogfirebase.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.blogfirebase.R
import com.example.blogfirebase.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding

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


            if (password != confirmPassword){

                binding.editTextConfirmPass.error = "The passwords are not the same"
                binding.editTextPasswordSignUp.error = "The passwords are not the same"
                return@setOnClickListener
            }

            if (username.isEmpty()){
                binding.editTextUsername.error = "Username is empty"
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.editTextEmailSignUp.error = "Email is empty"
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.editTextPasswordSignUp.error = "Password is empty"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()){
                binding.textInputConfirmPass.error = "Password is empty"
                return@setOnClickListener
            }

            Log.d("signUpData", "data: $username $email $password $confirmPassword")

        }

    }
}
