package eugene.bondarev.lifehacker.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import eugene.bondarev.lifehacker.data_layer.repository.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostViewModel: ViewModel() {
    companion object {
        const val TAG = "PostViewModel"
    }

    private val parentJob = Job()


    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : PostRepository = PostRepository()
    val postList = MutableLiveData<List<PostParcelable>>()

    fun fetchPosts(): MutableLiveData<List<PostParcelable>> {

        scope.launch {
            val posts = repository.getPosts()
            postList.postValue(posts)
            Log.e(TAG, "${posts?.size}")
        }
        return postList
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}