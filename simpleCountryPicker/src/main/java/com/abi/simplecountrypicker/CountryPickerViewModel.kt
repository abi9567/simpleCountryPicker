package com.abi.simplecountrypicker

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abi.simplecountrypicker.data.CountryData
import com.abi.simplecountrypicker.utils.Utils

class CountryPickerViewModel : ViewModel() {

    private val _isCountryPickerDialogVisible = mutableStateOf(value = true)
    val isCountryPickerDialogVisible : State<Boolean> = _isCountryPickerDialogVisible

    private val fullCountryList = Utils.countryList

    private val _countryList = MutableLiveData(fullCountryList)
    val countryList : LiveData<List<CountryData>> = _countryList

    private val _selectedCountry = MutableLiveData(countryList.value?.get(0))
    val selectedCountry : LiveData<CountryData?> = _selectedCountry


    fun setCountryPickerDialogVisibility() {
        _isCountryPickerDialogVisible.value = !_isCountryPickerDialogVisible.value
    }

    fun setSelectedCountry(item : CountryData) {
        _selectedCountry.value = item
    }

    fun resetCountry() {
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