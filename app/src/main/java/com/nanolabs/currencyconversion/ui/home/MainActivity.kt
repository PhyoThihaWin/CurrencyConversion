package com.nanolabs.currencyconversion.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.nanolabs.currencyconversion.R
import com.nanolabs.currencyconversion.model.ApiCurrency
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate
import com.nanolabs.currencyconversion.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    private lateinit var prefManager: PrefManager
    private lateinit var mainViewModel: MainViewModel
    lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        prefManager = PrefManager(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait..")
        progressDialog.setCancelable(false)

        setupUI()
        if (prefManager.getRefreshTime().isEmpty() || !prefManager.getRefreshTime().isHalfHour()) {
            mainViewModel.fetchSupportedCurrency(ConstantValue.apiKey)
        } else this.showToast("Time is ${prefManager.getRefreshTime()}")

    }

    private fun setupUI() {
        val spinnerCurrency: Spinner = findViewById(R.id.spinner_currency)

        val arr = arrayOf("Hello Myanmar", "Hi Myanmar", "GG Myanmar")
        val adapterChild = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arr)
        spinnerCurrency.adapter = adapterChild


//        spinnerAdult.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(
//                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
//            ) {
//                if (position == 0) {
//                    txtAdultPrice.text = packages.discount_price.currencyFormat()
//                    adPrice = packages.discount_price
//                    adult = 0
//                } else {
//                    txtAdultPrice.text = packages.singleRoomPrice.currencyFormat()
//                    adPrice = packages.singleRoomPrice
//                    adult = 1
//                }
//
//            }
//
//        }


        mainViewModel.apiCurrency.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    it.data?.let { apiCurrency ->
                        mainViewModel.insertCurrencyList(getCurrencyJson(apiCurrency.currencies))
                        Log.i("myDataCurrency", Gson().toJson(mainViewModel.getRateList()))
                        mainViewModel.fetchCurrencyRate(ConstantValue.apiKey)
                    }
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
                Status.ERROR -> {
                    //Handle Error
                    progressDialog.dismiss()
                    Log.i("myError", it.message.toString())
                }
            }
        })

        mainViewModel.apiRate.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    it.data?.let { apiRate ->
                        mainViewModel.insertRateList(getRateJson(apiRate.quotes))
                        Log.i("myDataRate", Gson().toJson(mainViewModel.getRateList()))
                        prefManager.setRefreshTime(Date().dateFormat())
                    }
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
                Status.ERROR -> {
                    //Handle Error
                    progressDialog.dismiss()
                    Log.i("myError", it.message.toString())
                }
            }
        })


    }

    fun getCurrencyJson(jsonObject: JsonObject): List<Currency> {
        val entries: Set<Map.Entry<String, JsonElement>> = jsonObject.entrySet()
        val currencyList: MutableList<Currency> = ArrayList()
        entries.forEach {
            currencyList.add(Currency(it.key, it.value.asString))
        }
        return currencyList
    }

    fun getRateJson(jsonObject: JsonObject): List<Rate> {
        val entries: Set<Map.Entry<String, JsonElement>> = jsonObject.entrySet()
        val rateList: MutableList<Rate> = ArrayList()
        entries.forEach {
            rateList.add(Rate(it.key, it.value.asDouble))
        }
        return rateList
    }
}