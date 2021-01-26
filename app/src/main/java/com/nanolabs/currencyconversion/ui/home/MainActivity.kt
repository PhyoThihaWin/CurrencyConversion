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
import com.nanolabs.currencyconversion.utils.ConstantValue
import com.nanolabs.currencyconversion.utils.Status
import com.nanolabs.currencyconversion.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel
    lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait..")
        progressDialog.setCancelable(false)

        setupUI()
        mainViewModel.fetchSupportedCurrency(ConstantValue.apiKey)

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
                        mainViewModel.insertCurrencyList(getJsonExtract(apiCurrency.currencies))
                        Log.i("myDataCurrency", Gson().toJson(mainViewModel.getCurrencyList()))
                    }
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
                Status.ERROR -> {
                    //Handle Error
                    progressDialog.dismiss()
                    Log.i("myError",it.message.toString())
                }
            }
        })


    }


//    private fun getSupportedCurrency() {
//        val progressDialog = ProgressDialog.show(this, "", "Please wait...")
//        val call = ConstantValue.api.getSupportedCurrency(ConstantValue.apiKey)
//        call.enqueue(object : Callback<ApiCurrency> {
//            override fun onResponse(call: Call<ApiCurrency>, response: Response<ApiCurrency>) {
//                progressDialog.dismiss()
//                if (response.body() != null) {
//                    Log.i("myCurrency1", response.body()!!.currencies.size().toString())
//                    Log.i(
//                        "myCurrency2",
//                        getJsonExtract(response.body()!!.currencies).size.toString()
//                    )
//                    Log.i(
//                        "myCurrency3",
//                        Gson().toJson(getJsonExtract(response.body()!!.currencies))
//                    )
//                } else Log.i("Retrofit error", response.errorBody().toString())
//            }
//
//            override fun onFailure(call: Call<ApiCurrency>, t: Throwable) {
//                Log.i("Retrofit error", t.message.toString())
//                if (t is IOException) {
//                    this@MainActivity.showToast("No internet connection.")
//                    progressDialog.dismiss()
//                } else Log.i("Retrofit error", t.message.toString())
//            }
//        })
//    }

    fun getJsonExtract(jsonObject: JsonObject): List<Currency> {
        val entries: Set<Map.Entry<String, JsonElement>> = jsonObject.entrySet()
        val currencyList: MutableList<Currency> = ArrayList()
        entries.forEach {
            currencyList.add(Currency(it.key, it.value.asString))
        }
        return currencyList
    }

}