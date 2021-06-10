package com.example.blogfirebase.domain.home

import com.example.blogfirebase.core.Resource
import com.example.blogfirebase.data.model.Post
import com.example.blogfirebase.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestPost(): Resource<List<Post>> = dataSource.getLatestPosts()
}