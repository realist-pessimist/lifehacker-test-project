package eugene.bondarev.lifehacker.data_layer.utils

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import eugene.bondarev.lifehacker.data_layer.remote.PlaceholderApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory{

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(OkHttpClient().newBuilder().build())
        .baseUrl("https://lifehacker.ru/api/wp/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val placeHolderApi : PlaceholderApi = retrofit().create(PlaceholderApi::class.java)
}