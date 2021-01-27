package com.nanolabs.currencyconversion.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class Currency(
        @PrimaryKey @ColumnInfo(name = "shortName") val shortName: String,
        @ColumnInfo(name = "fullName") val fullName: String
){
        override fun toString(): String {
                return "$shortName ($fullName)"
        }
}


