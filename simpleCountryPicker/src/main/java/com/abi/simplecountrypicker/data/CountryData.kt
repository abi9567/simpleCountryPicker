package com.abi.simplecountrypicker.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CountryData(
    val countryIdentifier : String,
    val countryCode : String,
    @DrawableRes val countryIcon : Int,
    @StringRes val countryName : Int,
)
