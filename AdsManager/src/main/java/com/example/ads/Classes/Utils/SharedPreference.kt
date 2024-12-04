package com.example.ads.Classes.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils

class SharedPreference constructor(private var context: Context) {
    private val preference: SharedPreferences? =
        context.getSharedPreferences(AppConstants.Local_DATA_NAME, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun stringValue(key: String, value: String) {
        if (preference != null && !TextUtils.isEmpty(key)) {
            val editor: SharedPreferences.Editor = preference.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }

    fun getString(key: String): String? {
        var value: String? = null
        if (preference != null && !TextUtils.isEmpty(key)) {
            value = preference.getString(key, AppConstants.DEFAULT_VALUE)
        }
        return value
    }

    fun setIntValue(key: String, value: Int) {
        if (preference != null && !TextUtils.isEmpty(key)) {
            val editor: SharedPreferences.Editor = preference.edit()
            editor.putInt(key, value)
            editor.apply()
        }
    }

    fun getLong(key: String): Int {
        var value: Int? = null
        if (preference != null && !TextUtils.isEmpty(key)) {
            value = preference.getInt(key, 0)
        }
        return value!!
    }

    fun setBoolean(key: String, value: Boolean) {
        if (preference != null && !TextUtils.isEmpty(key)) {
            var editor: SharedPreferences.Editor = preference.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

    }

    fun getBoolean(key: String): Boolean {
        var value: Boolean = false
        if (preference != null && !TextUtils.isEmpty(key)) {
            value = preference.getBoolean(key, false)
        }
        return value
    }

    fun deleteSingleValue(key: String) {
        if (preference != null && !TextUtils.isEmpty(key)) {
            val editor: SharedPreferences.Editor = preference.edit()
            editor.remove(key)
            editor.apply()
        }
    }

    fun clearShareDPreferences() {
        if (preference != null) {
            val editor: SharedPreferences.Editor = preference.edit()
            editor.clear()
            editor.apply()
        }
    }
}