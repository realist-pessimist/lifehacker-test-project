package eugene.bondarev.lifehacker.data_layer.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import eugene.bondarev.lifehacker.base.BaseRepository
import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import eugene.bondarev.lifehacker.data_layer.utils.ApiFactory
import eugene.bondarev.lifehacker.ui.post.PostActivity


class PostRepository() : BaseRepository() {
    companion object {
        const val TAG = "PostRepository"
    }

    private val service = ApiFactory.placeHolderApi

    suspend fun getPosts() : List<PostParcelable>?{
        var posts: List<PostParcelable>? = null
        val postRequest = service.getPostsAsync() // Making Network call
        try {
            val response = postRequest.await()
            if (response.isSuccessful){
                posts = response.body() // This is List<PostParcelable>
            }else{
                Log.e(TAG,response.errorBody().toString())
            }
        }catch (e: Exception){

        }
        return posts
    }

    suspend fun getPost(id: Long?) : PostParcelable?{
        var post: PostParcelable? = null
        val postRequest = service.getPostAsync(id)
        try {
            val response = postRequest.await()
            if (response.isSuccessful){
                post = response.body() // This is PostParcelable
            }else{
                Log.e(TAG,response.errorBody().toString())
            }
        }catch (e: Exception){

        }
        return post
    }
}