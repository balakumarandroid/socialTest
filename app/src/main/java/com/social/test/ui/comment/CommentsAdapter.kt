package com.social.test.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.social.test.R
import com.social.test.data.model.CommentsResponseItem

class CommentListAdapter(private val list: List<CommentsResponseItem>?) :
    RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return UserViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val postData: CommentsResponseItem? = list?.get(position)
        holder.bind(postData)
    }

    override fun getItemCount(): Int {
        return if ((list != null)) {
            list.size
        } else
            0

    }

}

class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_comments, parent, false)) {
    private var tvName: AppCompatTextView? = null
    private var tvEmail: AppCompatTextView? = null
    private var tvComment: AppCompatTextView? = null

    init {
        tvName = itemView.findViewById(R.id.tv_username)
        tvEmail = itemView.findViewById(R.id.tv_email)
        tvComment = itemView.findViewById(R.id.tv_comment)
    }

    fun bind(item: CommentsResponseItem?) {
        tvName?.text = item?.name
        tvEmail?.text = item?.email
        tvComment?.text = item?.body

    }

}