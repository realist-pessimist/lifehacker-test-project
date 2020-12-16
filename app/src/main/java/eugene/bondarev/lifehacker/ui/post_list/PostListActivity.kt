package eugene.bondarev.lifehacker.ui.post_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eugene.bondarev.lifehacker.R
import eugene.bondarev.lifehacker.ui.post.PostActivity

class PostListActivity : AppCompatActivity() {
    companion object {
        const val TAG = "PostListActivity"

        fun getPostListIntent(context: Context): Intent {
            return Intent(context, PostListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
    }
}