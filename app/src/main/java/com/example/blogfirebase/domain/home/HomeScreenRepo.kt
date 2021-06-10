package com.example.blogfirebase.domain.home

import com.example.blogfirebase.core.Result
import com.example.blogfirebase.data.model.Post

interface HomeScreenRepo {

    suspend fun getLatestPost(): Result<List<Post>>
}