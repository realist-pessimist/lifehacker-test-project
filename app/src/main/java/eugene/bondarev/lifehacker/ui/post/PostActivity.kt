package eugene.bondarev.lifehacker.ui.post

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import eugene.bondarev.lifehacker.R
import eugene.bondarev.lifehacker.ui.PostViewModel
import eugene.bondarev.lifehacker.ui.post_list.PostListActivity
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
        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        val postID: Long? = intent?.extras?.getLong("postId")
        setContentView(R.layout.activity_post)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);

        toolbar.setNavigationOnClickListener {
            startActivity(Intent(PostListActivity.getPostListIntent(this)))
        }

        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postID.let{postViewModel.fetchPostById(it)}

        postViewModel.receivedPost.observe(this, Observer {
            val pageContent = formattingWebView(it.title.rendered, it.content.rendered)
            contentView.loadDataWithBaseURL(null, pageContent, "text/html", "utf-8", null)
            contentView.isVerticalScrollBarEnabled = true
            contentView.isHorizontalScrollBarEnabled = true
        })
    }

    private fun formattingWebView(title :String, content: String): String{
        val head = "<h1>"
        val headClose = "</h1>"
        val titleWithHead = head.plus(title).plus(headClose + "\n")
        val pageContent = titleWithHead.plus(content)
        return pageContent
    }

}