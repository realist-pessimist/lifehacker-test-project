package eugene.bondarev.lifehacker.data_layer.remote

import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceholderApi{
    @GET("api/wp/v2/posts")
    fun getPostsAsync() : Deferred<Response<List<PostParcelable>>>

    @GET("api/wp/v2/posts/{id}")
    fun getPost(@Path("id") id: Int) : Deferred<Response<List<PostParcelable>>>
}