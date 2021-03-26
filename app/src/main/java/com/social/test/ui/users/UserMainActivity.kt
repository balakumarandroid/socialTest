package com.social.test.ui.users

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.test.R
import com.social.test.api.ApiClient
import com.social.test.api.ApiService
import com.social.test.data.model.UserResponseItem
import com.social.test.utils.AppUtils
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserMainActivity : AppCompatActivity() {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_users

    var usersListResponse: List<UserResponseItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        init()

    }

    private fun init() {

        if (AppUtils.isNetworkConnected(this)) {
            callUsersApi()
        } else {
            Toast.makeText(
                applicationContext,
                applicationContext.getString(R.string.no_connection),
                Toast.LENGTH_SHORT
            ).show()

        }
    }


    private fun callUsersApi() {
        progressBar.visibility = View.VISIBLE
        val apiInterfaces = ApiClient.getClient().create(ApiService::class.java)
        val call = apiInterfaces.getUsers()
        call.enqueue(object : Callback<List<UserResponseItem>> {
            override fun onResponse(
                call: Call<List<UserResponseItem>>?,
                response: Response<List<UserResponseItem>>?
            ) {
                progressBar.visibility = View.GONE
                if (response != null && response.isSuccessful) {
                    if (response.body() != null) {
                        usersListResponse = response.body()
                        rv_users.apply {
                            layoutManager = LinearLayoutManager(applicationContext)
                            adapter =
                                UserAdapter(usersListResponse)
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            applicationContext.getString(R.string.something_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<UserResponseItem>>?, t: Throwable?) {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    applicationContext.getString(R.string.something_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
}