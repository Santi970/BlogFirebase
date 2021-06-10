package com.example.blogfirebase.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.blogfirebase.core.Resource
import com.example.blogfirebase.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(private val repo: HomeScreenRepo): ViewModel() {

    fun fetchLatestPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(repo.getLatestPost())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }
}
//creamos una instancia del view model de la implementacion concreta de donde esta ubicada la interfaz (o sea en el implement)
class HomeScreenViewModelFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)

    }
}