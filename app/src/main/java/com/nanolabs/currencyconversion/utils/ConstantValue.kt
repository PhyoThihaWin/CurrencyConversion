package com.nanolabs.currencyconversion.utils

import com.nanolabs.currencyconversion.api.Api
import com.nanolabs.currencyconversion.api.ApiClient

object ConstantValue {
    val apiKey = "a483487fc89314bd19f2e8d7ed666297"

    val api: Api = ApiClient.client.create(Api::class.java)
}