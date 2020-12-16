package eugene.bondarev.lifehacker.data_layer.repository

import eugene.bondarev.lifehacker.base.BaseRepository
import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import eugene.bondarev.lifehacker.data_layer.utils.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostRepository() : BaseRepository() {

    fun getPosts() : List<PostParcelable>?{
        val service = ApiFactory.placeHolderApi
        var posts: List<PostParcelable>? = null
        GlobalScope.launch(Dispatchers.Main) {
            val postRequest = service.getPosts() // Making Network call
            try {
                val response = postRequest.await()
                posts = response.body() // This is List<PlaceholderPosts>
            }catch (e: Exception){

            }
        }
        return posts
    }

}