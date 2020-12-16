package eugene.bondarev.lifehacker.ui.post_list

import android.graphics.Color
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

class PostListRecyclerAdapter @Inject constructor()
    : BaseRecyclerAdapter<PostListRecyclerAdapter.PostHolder, PostParcelable>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindProduct(get(position))
    }

    override fun getItemId(position: Int): Long {
        return get(position).id.toLong()
    }

    inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindProduct(postParcelable: PostParcelable){
            Glide.with(itemView)
                .load(postParcelable.cat_cover.sizes.mobile)
                .into(itemView.postImage)
            itemView.postTitle
        }
    }
}