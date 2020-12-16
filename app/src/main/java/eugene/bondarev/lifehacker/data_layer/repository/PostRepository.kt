package eugene.bondarev.lifehacker.data_layer.repository

import android.util.Log
import eugene.bondarev.lifehacker.base.BaseRepository
import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import eugene.bondarev.lifehacker.data_layer.utils.ApiFactory


class PostRepository() : BaseRepository() {

    suspend fun getPosts() : List<PostParcelable>?{
        val service = ApiFactory.placeHolderApi
        var posts: List<PostParcelable>? = null

        val postRequest = service.getPostsAsync() // Making Network call
        try {
            val response = postRequest.await()
            if (response.isSuccessful){
                posts = response.body() // This is List<PlaceholderPosts>
            }else{
                Log.d("MainActivity ",response.errorBody().toString())
            }
        }catch (e: Exception){

        }
        return posts
    }

}