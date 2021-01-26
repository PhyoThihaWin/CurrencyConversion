package com.nanolabs.currencyconversion.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class PrefManager(private val context: Context) {
    var preference: SharedPreferences
    var editor: Editor

    // shared pref mode
    var PRIVATE_MODE = 0

    // Shared preferences file name
    private val PREF_NAME = "pthw-currency"
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    private val TIME = "refresh_time"


    init {
        preference = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = preference.edit()
    }


    fun setRefreshTime(time: String) {
        editor.putString(TIME, time)
        editor.commit()
    }

    fun getRefreshTime(): String {
        return preference.getString(TIME, "")!!
    }


}