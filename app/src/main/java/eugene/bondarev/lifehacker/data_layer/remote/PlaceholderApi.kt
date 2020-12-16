package eugene.bondarev.lifehacker.data_layer.remote

import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceholderApi{
    @GET("/posts")
    fun getPosts() : Deferred<Response<List<PostParcelable>>>

    @GET("/posts/{id}")
    fun getPost(@Path("id") id: Int) : Deferred<Response<List<PostParcelable>>>
}