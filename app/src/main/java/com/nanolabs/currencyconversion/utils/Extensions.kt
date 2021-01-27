package com.nanolabs.currencyconversion.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

//--hide keyboard method
fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Date.dateFormat(): String {
    val desirFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    return desirFormat.format(this)
}


fun String.isHalfHour(): Boolean {
    return if (this.isNotEmpty()) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val saveTime = sdf.parse(this)
        val currentTime: Date = sdf.parse(sdf.format(Date()))

        val calendar = Calendar.getInstance()
        calendar.time = saveTime
        calendar.add(Calendar.MINUTE, +30)

        Log.i("myTime", calendar.time.toString())
        Log.i("myTime", currentTime.toString())

        currentTime.before(calendar.time)
    } else false
}

fun Double.formatDecimal():String{
    val formatter1 = DecimalFormat("0.00")
    var formatter2 = DecimalFormat("#,###.##")
    return (formatter2.format(this))
}

