package com.nanolabs.currencyconversion.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class Currency(
        @PrimaryKey @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "full") val full: String
)
