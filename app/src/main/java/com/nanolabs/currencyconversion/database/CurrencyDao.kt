package com.nanolabs.currencyconversion.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate


@Dao
interface CurrencyDao {
    @Query("SELECT * from Currency")
    fun getCurrencyList(): List<Currency>

    @Query("SELECT * from Rate")
    fun getRateList(): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyList(currencys: List<Currency>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateList(currencys: List<Rate>)
}