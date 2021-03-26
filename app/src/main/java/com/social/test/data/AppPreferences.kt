package com.social.test.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.social.test.BuildConfig
import com.social.test.data.model.PhotosResponseItem


object AppPreferences {
    private const val NAME = BuildConfig.PREF_NAME
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var mPrefs: SharedPreferences

    fun init(context: Context) {
        mPrefs = context.getSharedPreferences(
            NAME,
            MODE
        )
    }

    private const val PREF_KEY_PHOTO = "PREF_KEY_PHOTO_LIST"


    var photoList: List<PhotosResponseItem>?
        get() {
            return try {

                val type = object : TypeToken<List<PhotosResponseItem>>() {

                }.type
                Gson().fromJson(mPrefs.getString(PREF_KEY_PHOTO, ""), type)
            } catch (e: Exception) {
                ArrayList()
            }
        }
        set(photoList) {
            mPrefs.edit().putString(PREF_KEY_PHOTO, Gson().toJson(photoList)).apply()
        }

}