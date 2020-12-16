package eugene.bondarev.lifehacker.ui.post

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eugene.bondarev.lifehacker.R

class PostActivity : AppCompatActivity() {
    companion object {
        const val TAG = "PostActivity"

        fun getPostIntent(context: Context): Intent {
            return Intent(context, PostActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
    }
}