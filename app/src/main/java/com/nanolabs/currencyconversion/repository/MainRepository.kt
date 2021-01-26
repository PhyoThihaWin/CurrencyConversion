package com.nanolabs.currencyconversion.repository

import android.app.ProgressDialog
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nanolabs.currencyconversion.api.Api
import com.nanolabs.currencyconversion.database.CurrencyDao
import com.nanolabs.currencyconversion.model.ApiCurrency
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.utils.ConstantValue
import com.nanolabs.currencyconversion.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainRepository(val currencyDao: CurrencyDao, val api: Api) {
//    fun getSelectedDream(word: String): LiveData<List<Dream>> = dreamDao.getSelectedDream(word)


    suspend fun fetchSupportedCurrency(key: String): Response<ApiCurrency> = api.getSupportedCurrency(key)


    fun insertCurrencyList(currencyList: List<Currency>) {
        currencyDao.insertCurrencyList(currencyList)
    }

    fun getCurrencyList() = currencyDao.getCurrencyList()
}