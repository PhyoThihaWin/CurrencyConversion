package com.nanolabs.currencyconversion.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ApiRate(
        @SerializedName("success") val success: Boolean,
        @SerializedName("quotes") val quotes: JsonObject
)
