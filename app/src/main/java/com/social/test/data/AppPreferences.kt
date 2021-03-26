package com.social.test.data

import android.content.Context
import android.content.SharedPreferences
import com.social.test.BuildConfig


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

}