package com.example.blogfirebase.domain.home

import com.example.blogfirebase.core.Result
import com.example.blogfirebase.data.model.Post
import com.example.blogfirebase.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestPost(): Result<List<Post>> = dataSource.getLatestPosts()
}