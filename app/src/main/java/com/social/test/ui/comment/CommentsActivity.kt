package com.social.test.ui.comment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.test.R
import com.social.test.api.ApiClient
import com.social.test.api.ApiService
import com.social.test.data.model.CommentsResponseItem
import com.social.test.utils.AppConstants
import com.social.test.utils.AppUtils
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_users

    private lateinit var postId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        val bundle = intent.extras
        if (bundle != null) {
            postId = bundle.getString(AppConstants.postID).toString()
        }

        init()

    }

    private fun init() {
        if (AppUtils.isNetworkConnected(this)) {
            callCommentsApi(postId)
        } else {
            Toast.makeText(
                applicationContext,
                applicationContext.getString(R.string.no_connection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun callCommentsApi(postID: String?) {
        progressBar.visibility = View.VISIBLE
        val apiInterfaces = ApiClient.getClient().create(ApiService::class.java)
        val call = apiInterfaces.getComments(postID)
        call.enqueue(object : Callback<List<CommentsResponseItem>> {
            override fun onResponse(
                call: Call<List<CommentsResponseItem>>?,
                response: Response<List<CommentsResponseItem>>?
            ) {
                progressBar.visibility = View.GONE
                if (response != null && response.isSuccessful) {
                    if (response.body() != null) {
                        rv_users.apply {
                            layoutManager = LinearLayoutManager(applicationContext)
                            adapter = CommentListAdapter(response.body())
                        }

                    } else {
                        Toast.makeText(
                            applicationContext,
                            applicationContext!!.getString(R.string.something_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<CommentsResponseItem>>?, t: Throwable?) {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    applicationContext!!.getString(R.string.something_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
}