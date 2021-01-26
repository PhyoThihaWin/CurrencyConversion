package com.nanolabs.currencyconversion.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nanolabs.currencyconversion.model.Currency


@Dao
interface CurrencyDao {
    @Query("SELECT * from Currency")
    fun getCurrencyList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyList(currencys: List<Currency>)
}