package com.nanolabs.currencyconversion.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate


@Dao
interface CurrencyDao {
    @Query("SELECT * from Currency")
    fun getCurrencyList(): LiveData<List<Currency>>

    @Query("SELECT * from Rate")
    fun getRateList(): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyList(currencys: List<Currency>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateList(currencys: List<Rate>)

    @Query("SELECT rate from Rate where rateName LIKE '%' || :currency || '%'")
    fun getRateByName(currency: String): Double

    @Query("SELECT fullName from Currency where shortName=:shortName")
    fun getFullName(shortName: String): String
}