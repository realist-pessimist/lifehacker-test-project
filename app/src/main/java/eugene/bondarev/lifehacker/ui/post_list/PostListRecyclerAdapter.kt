package eugene.bondarev.lifehacker.ui.post_list


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eugene.bondarev.lifehacker.R
import eugene.bondarev.lifehacker.base.BaseRecyclerAdapter
import eugene.bondarev.lifehacker.data_layer.dto.PostParcelable
import kotlinx.android.synthetic.main.post_item.view.*
import javax.inject.Inject


class PostListRecyclerAdapter @Inject constructor(val mAdapterCallback: AdapterCallback)
    : BaseRecyclerAdapter<PostListRecyclerAdapter.PostHolder, PostParcelable>() {

    companion object {
        const val TAG = "PostListRecyclerAdapter"
    }

    interface AdapterCallback {
        fun onMethodCallback(postId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindPost(get(position))

        holder.itemView.setOnClickListener {
            mAdapterCallback.onMethodCallback(getItemId(position))
        }

    }

    override fun getItemId(position: Int): Long {
        return get(position).id.toLong()
    }


    inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindPost(postParcelable: PostParcelable){
            val responseImage: String? = postParcelable.cat_cover.sizes.mobile

            Log.d(TAG, "responseImage = $responseImage")

            if (responseImage == null) {
                itemView.postImage.setImageResource(R.drawable.default_image_post_foreground)
            }
            else {
                Glide.with(itemView)
                    .load(responseImage)
                    .into(itemView.postImage)
            }
            itemView.postTitle.text = postParcelable.title.rendered
        }
    }
}