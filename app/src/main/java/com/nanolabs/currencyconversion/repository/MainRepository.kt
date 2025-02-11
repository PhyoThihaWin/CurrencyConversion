package com.nanolabs.currencyconversion.repository

import android.app.ProgressDialog
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nanolabs.currencyconversion.api.Api
import com.nanolabs.currencyconversion.database.CurrencyDao
import com.nanolabs.currencyconversion.model.ApiCurrency
import com.nanolabs.currencyconversion.model.ApiRate
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate
import com.nanolabs.currencyconversion.utils.ConstantValue
import com.nanolabs.currencyconversion.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainRepository(val currencyDao: CurrencyDao, val api: Api) {
//    fun getSelectedDream(word: String): LiveData<List<Dream>> = dreamDao.getSelectedDream(word)


    suspend fun fetchSupportedCurrency(key: String): Response<ApiCurrency> =
        api.getSupportedCurrency(key)

    suspend fun fetchCurrencyRate(key: String): Response<ApiRate> = api.getCurrencyRate(key)


    suspend fun insertCurrencyList(currencyList: List<Currency>) {
        currencyDao.insertCurrencyList(currencyList)
    }

    suspend fun insertRateList(rateList: List<Rate>) {
        currencyDao.insertRateList(rateList)
    }

    val currencyList = currencyDao.getCurrencyList()
    val rateList = currencyDao.getRateList()

    fun getRateByCurrency(currency: String) = currencyDao.getRateByName(currency)

    fun getFullName(name:String) = currencyDao.getFullName(name)
}