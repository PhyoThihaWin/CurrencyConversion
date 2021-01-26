package com.nanolabs.currencyconversion.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class ApiCurrency(
        @SerializedName("success") val success: Boolean,
        @SerializedName("currencies") val currencies: JsonObject
)
