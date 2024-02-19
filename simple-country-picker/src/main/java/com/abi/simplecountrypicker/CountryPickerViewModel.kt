package com.abi.simplecountrypicker

import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.telephony.TelephonyManager
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abi.simplecountrypicker.data.CountryData
import com.abi.simplecountrypicker.utils.Utils
import java.util.Locale


class CountryPickerViewModel : ViewModel() {

    private val _isCountryPickerDialogVisible = mutableStateOf(value = false)
    val isCountryPickerDialogVisible : State<Boolean> = _isCountryPickerDialogVisible

    private val fullCountryList = Utils.countryList

    private val _countryList = MutableLiveData(fullCountryList)
    val countryList : LiveData<List<CountryData>> = _countryList

    private val _selectedCountry = MutableLiveData(countryList.value?.get(0))
    val selectedCountry : LiveData<CountryData?> = _selectedCountry


    fun getDefaultCountry(context : Context) {
        val telephonyManager = getSystemService(context, TelephonyManager::class.java)
        Log.d("TelephonyManager", "${ telephonyManager?.simCountryIso }")
    }

    fun setInitialCountry(countryIdentifier : String) {
        val defaultCountry = fullCountryList.find {
            it.countryIdentifier == countryIdentifier.lowercase(Locale.ROOT)
        }
        defaultCountry?.let {
            _selectedCountry.value = defaultCountry
        }
    }

    fun setCountryPickerDialogVisibility() {
        _isCountryPickerDialogVisible.value = !_isCountryPickerDialogVisible.value
    }

    fun setSelectedCountry(item : CountryData) {
        _selectedCountry.value = item
    }

    fun resetCountryList() {
        _countryList.value = fullCountryList
    }

    fun searchCountry(searchQuery : String, context : Context) {
        val tempList = mutableListOf<CountryData>()
        fullCountryList.forEach {
            val countryName = context.getString(it.countryName)
            if (countryName.contains(other = searchQuery, ignoreCase = true)) {
                tempList.add(element = it)
            }
        }
        _countryList.value = tempList
    }

}