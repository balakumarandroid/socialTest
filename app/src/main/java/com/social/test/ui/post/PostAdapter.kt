package com.social.test.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.social.test.R
import com.social.test.data.model.PhotosResponseItem
import com.social.test.data.model.PostResponseItem
import com.squareup.picasso.Picasso


class PostListAdapter(
    private val userName: String?,
    private val list: List<PostResponseItem>?,
    private val userPhotoList: List<PhotosResponseItem>?
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postData: PostResponseItem? = list?.get(position)
        val photoData: PhotosResponseItem? = userPhotoList?.get(position)
        val userName: String? = userName
        holder.bind(userName, postData, photoData)
    }

    override fun getItemCount(): Int {
        return if ((list != null)) {
            list.size
        } else
            0

    }

}

class PostViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_post, parent, false)) {
    private var btnComment: AppCompatButton? = null
    private var ivPoster: AppCompatImageView? = null
    private var tvUserName: AppCompatTextView? = null
    private var tvTitle: AppCompatTextView? = null
    private var tvBody: AppCompatTextView? = null

    init {
        btnComment = itemView.findViewById(R.id.btn_comment)
        ivPoster = itemView.findViewById(R.id.iv_poster)
        tvUserName = itemView.findViewById(R.id.tv_username)
        tvTitle = itemView.findViewById(R.id.tv_title)
        tvBody = itemView.findViewById(R.id.tv_body)
    }

    fun bind(userName: String?, postList: PostResponseItem?, photoList: PhotosResponseItem?) {
        tvUserName?.text = userName
        tvTitle?.text = postList?.title
        tvBody?.text = postList?.body

        ivPoster?.let {
            Picasso.with(it.context)
                .load(postList?.userImage)
                .into(it)
        }

    }

}