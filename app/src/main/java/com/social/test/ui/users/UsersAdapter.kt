package com.social.test.ui.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.social.test.R
import com.social.test.data.model.UserResponseItem
import com.social.test.ui.post.PostActivity
import com.social.test.utils.AppConstants


class UserAdapter(private val list: List<UserResponseItem>?) :
    RecyclerView.Adapter<UserViewHolder>() {
    var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mContext = parent.context
        return UserViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData: UserResponseItem? = list?.get(position)
        holder.bind(userData)
    }

    override fun getItemCount(): Int {
        if ((list != null)) {
            return list.size
        } else
            return 0

    }

}

class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_user, parent, false)) {
    private var tvName: AppCompatTextView? = null
    private var tvEmail: AppCompatTextView? = null
    private var tvPhone: AppCompatTextView? = null
    private var tvWebsite: AppCompatTextView? = null
    private var cardView: CardView? = null

    init {
        cardView = itemView.findViewById(R.id.cv_item)
        tvName = itemView.findViewById(R.id.tv_username)
        tvEmail = itemView.findViewById(R.id.tv_email)
        tvPhone = itemView.findViewById(R.id.tv_phone)
        tvWebsite = itemView.findViewById(R.id.tv_website)
    }

    fun bind(usersList: UserResponseItem?) {
        tvName?.text = usersList?.username
        tvEmail?.text = usersList?.email
        tvPhone?.text = usersList?.phone
        tvWebsite?.text = usersList?.website

        cardView?.setOnClickListener {
            val intent = Intent(it.context, PostActivity::class.java)
            val bundle = Bundle()
            bundle.putString(AppConstants.UserID, usersList?.id.toString())
            bundle.putString(AppConstants.UserName, usersList?.name)
            intent.putExtras(bundle)
            it.context.startActivity(intent)

        }

    }

}