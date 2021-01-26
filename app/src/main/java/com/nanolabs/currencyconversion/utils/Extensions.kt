package com.nanolabs.currencyconversion.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

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

