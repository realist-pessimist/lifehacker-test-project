package eugene.bondarev.lifehacker.ui.post

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import eugene.bondarev.lifehacker.R
import eugene.bondarev.lifehacker.ui.PostViewModel
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    companion object {
        const val TAG = "PostActivity"

        fun getPostIntent(context: Context): Intent {
            return Intent(context, PostActivity::class.java)
        }
    }
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postID: Long? = intent?.extras?.getLong("postId")
        setContentView(R.layout.activity_post)

        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postID.let{postViewModel.fetchPostById(it)}

        postViewModel.receivedPost.observe(this, Observer {
            titleView.text = it.title.toString()
            contentView.text = it.content.toString()
        })
    }

}