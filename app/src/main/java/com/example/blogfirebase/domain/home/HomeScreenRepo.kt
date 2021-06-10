package com.example.blogfirebase.domain.home

import com.example.blogfirebase.core.Resource
import com.example.blogfirebase.data.model.Post

interface HomeScreenRepo {

    suspend fun getLatestPost(): Resource<List<Post>>
}