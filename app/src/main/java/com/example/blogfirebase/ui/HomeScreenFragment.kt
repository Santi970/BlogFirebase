package com.example.blogfirebase.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.blogfirebase.core.Result
import com.example.blogfirebase.R
import com.example.blogfirebase.data.remote.home.HomeScreenDataSource
import com.example.blogfirebase.databinding.FragmentHomeScreenBinding
import com.example.blogfirebase.domain.home.HomeScreenRepoImpl
import com.example.blogfirebase.presentation.main.HomeScreenViewModel
import com.example.blogfirebase.presentation.main.HomeScreenViewModelFactory
import com.example.blogfirebase.ui.main.adapters.HomeScreenAdapter



class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel>{ HomeScreenViewModelFactory(
        HomeScreenRepoImpl(
        HomeScreenDataSource()
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is  Result.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE

                }
                is  Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
            }
                is  Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Ocurrio un error: ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }
}