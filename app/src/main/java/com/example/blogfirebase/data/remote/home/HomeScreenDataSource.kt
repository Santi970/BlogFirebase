package com.example.blogfirebase.data.remote.home

import com.example.blogfirebase.core.Result
import com.example.blogfirebase.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestPosts(): Result<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot.documents) { //querySnapshot es de firebase ( contiene los documentos)
            post.toObject(Post::class.java)?.let { fbPost ->
                postList.add(fbPost)
            }
        }
        return  Result.Success(postList)
    }
}
