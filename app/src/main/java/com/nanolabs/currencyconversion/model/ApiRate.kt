package com.nanolabs.currencyconversion.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class ApiRate(
        @SerializedName("success") val success: Boolean,
        @SerializedName("quotes") val quotes: JSONObject
)
