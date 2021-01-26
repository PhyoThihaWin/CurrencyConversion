package com.nanolabs.currencyconversion.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nanolabs.currencyconversion.database.AppDatabase
import com.nanolabs.currencyconversion.model.ApiCurrency
import com.nanolabs.currencyconversion.model.ApiRate
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate
import com.nanolabs.currencyconversion.repository.MainRepository
import com.nanolabs.currencyconversion.utils.ConstantValue
import com.nanolabs.currencyconversion.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: MainRepository
//    val allNumbers: LiveData<List<Numbers>>


    init {
        val currencyDao = AppDatabase.getDatabase(application).currencyDao()
        mRepository = MainRepository(currencyDao, ConstantValue.api)
//        allNumbers = mRepository.allNumbers
    }


    private val _apiCurrency = MutableLiveData<Resource<ApiCurrency>>()
    val apiCurrency: LiveData<Resource<ApiCurrency>> get() = _apiCurrency

    fun fetchSupportedCurrency(key: String) {
        viewModelScope.launch {
            _apiCurrency.postValue(Resource.loading(null))
            try {
                mRepository.fetchSupportedCurrency(key).let {
                    if (it.isSuccessful) {
                        _apiCurrency.postValue(Resource.success(it.body()))
                    } else _apiCurrency.postValue(
                        Resource.error(it.errorBody().toString(), null)
                    )
                }
            } catch (e: Exception) {
                _apiCurrency.postValue(Resource.error(e.message.toString(), null))
            }

        }
    }


    private val _apiRate = MutableLiveData<Resource<ApiRate>>()
    val apiRate: LiveData<Resource<ApiRate>> get() = _apiRate

    fun fetchCurrencyRate(key: String) {
        viewModelScope.launch {
            _apiRate.postValue(Resource.loading(null))
            try {
                mRepository.fetchCurrencyRate(key).let {
                    if (it.isSuccessful) {
                        _apiRate.postValue(Resource.success(it.body()))
                    } else _apiRate.postValue(
                        Resource.error(it.errorBody().toString(), null)
                    )
                }
            } catch (e: Exception) {
                _apiRate.postValue(Resource.error(e.message.toString(), null))
            }

        }
    }


    fun insertCurrencyList(currencyList: List<Currency>) {
        viewModelScope.launch {
            mRepository.insertCurrencyList(currencyList)
        }
    }

    fun insertRateList(rateList: List<Rate>) {
        viewModelScope.launch {
            mRepository.insertRateList(rateList)
        }
    }
//
//    fun deleteAll() {
//        mRepository.deleteAll()
//    }


    fun getCurrencyList() = mRepository.getCurrencyList()
    fun getRateList() = mRepository.getRateList()
}