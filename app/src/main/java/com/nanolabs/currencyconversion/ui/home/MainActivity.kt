package com.nanolabs.currencyconversion.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.nanolabs.currencyconversion.R
import com.nanolabs.currencyconversion.model.ApiCurrency
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate
import com.nanolabs.currencyconversion.ui.home.adapter.AmountAdapter
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

    var currencyList: List<Currency> = ArrayList()
    var selectedCurrencyRate = 0.0
    var selectedCurrency = ""

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
        }

    }

    private fun setupUI() {
        val spinnerCurrency: Spinner = findViewById(R.id.spinner_currency)
        val btnCal: Button = findViewById(R.id.btn_cal)
        val etCurrency: EditText = findViewById(R.id.et_currency)
        val recycler: RecyclerView = findViewById(R.id.recycler)



        spinnerCurrency.setSelection(0)
        spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedCurrencyRate = if (currencyList[position].shortName != "USD")
                    mainViewModel.getRateByCurrency(currencyList[position].shortName) else 1.0
                selectedCurrency = currencyList[position].shortName
            }

        }



        btnCal.setOnClickListener {
            if (etCurrency.text.toString().isNotEmpty()) {
                Log.i(
                    "myRate",
                    (etCurrency.text.toString().toDouble() / selectedCurrencyRate).toString()
                )
                val adapter = AmountAdapter(
                    this, selectedCurrency,
                    etCurrency.text.toString().toDouble() / selectedCurrencyRate,
                    mainViewModel.rateList
                )
                recycler.adapter = adapter

                it.hideKeyboard()
            }else this.showToast("No currency amount!")
        }




        mainViewModel.currencyList.observe(this, Observer {
            val adapterChild = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, it)
            spinnerCurrency.adapter = adapterChild
            currencyList = it
        })

        mainViewModel.apiCurrency.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    it.data?.let { apiCurrency ->
                        mainViewModel.insertCurrencyList(getCurrencyJson(apiCurrency.currencies))
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
                        prefManager.setRefreshTime(Date().dateFormat())
                        this.showToast("Up to date Rate and refresh after 30 minutes.")
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
            rateList.add(Rate(it.key, it.value.asDouble, mainViewModel.getFullName(it.key.substring(3,6))))
        }
        return rateList
    }
}