package com.nanolabs.currencyconversion.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rate")
data class Rate(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rate") val rate: Double
)
