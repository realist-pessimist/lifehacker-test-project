package eugene.bondarev.lifehacker.ui.post_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import eugene.bondarev.lifehacker.R
import eugene.bondarev.lifehacker.ui.PostViewModel
import eugene.bondarev.lifehacker.ui.post.PostActivity
import kotlinx.android.synthetic.main.activity_post_list.*


class PostListActivity : AppCompatActivity(), PostListRecyclerAdapter.AdapterCallback {
    companion object {
        const val TAG = "PostListActivity"

        fun getPostListIntent(context: Context): Intent {
            return Intent(context, PostListActivity::class.java)
        }
    }

    private var mPostListRecyclerAdapter = PostListRecyclerAdapter(this)
    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        postsRecyclerView.adapter = mPostListRecyclerAdapter
        postsRecyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        mPostListRecyclerAdapter.clearWithoutNotify()
        postViewModel.fetchPosts()

        postViewModel.postList.observe(this, Observer {
            mPostListRecyclerAdapter.addAll(it)
        })
    }


    override fun onMethodCallback(postId: Long) {
        val intent = PostActivity.getPostIntent(this)
        startActivity(intent.putExtra("postId", postId))
    }
}