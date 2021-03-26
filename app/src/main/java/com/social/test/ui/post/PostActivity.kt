package com.social.test.ui.post

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.test.R
import com.social.test.api.ApiClient
import com.social.test.api.ApiService
import com.social.test.data.AppPreferences
import com.social.test.data.model.PhotosResponseItem
import com.social.test.data.model.PostResponseItem
import com.social.test.utils.AppConstants
import com.social.test.utils.AppUtils
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_users

    private lateinit var userId: String
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        val bundle = intent.extras
        if (bundle != null) {
            userId = bundle.getString(AppConstants.UserID).toString()
            userName = bundle.getString(AppConstants.UserName).toString()
        }

        init()

    }

    private fun init() {
        if (AppUtils.isNetworkConnected(this)) {
            callPostApi(userId, userName, AppPreferences.photoList)
        } else {
            Toast.makeText(
                applicationContext,
                applicationContext.getString(R.string.no_connection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun callPostApi(userId: String?, name: String?, photoList: List<PhotosResponseItem>?) {
        progressBar.visibility = View.VISIBLE
        val apiInterfaces = ApiClient.getClient().create(ApiService::class.java)
        val call = apiInterfaces.getPostList(userId)
        call.enqueue(object : Callback<List<PostResponseItem>> {
            override fun onResponse(
                call: Call<List<PostResponseItem>>?,
                response: Response<List<PostResponseItem>>?
            ) {
                progressBar.visibility = View.GONE
                if (response != null && response.isSuccessful) {
                    if (response.body() != null) {
                        val postList = response.body()
                        if (postList != null && photoList != null)
                            for (postItem in postList) {
                                for (photoItem in photoList) {
                                    if (postItem.id == photoItem.id) {
                                        postItem.userImage = photoItem.url
                                    }
                                }
                            }

                        rv_users.apply {
                            layoutManager = LinearLayoutManager(applicationContext)
                            adapter = PostListAdapter(name, postList, photoList)
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

            override fun onFailure(call: Call<List<PostResponseItem>>?, t: Throwable?) {
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