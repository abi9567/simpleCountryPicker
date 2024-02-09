package com.abi.simplecountrypicker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abi.simplecountrypicker.data.CountryData
import com.abi.simplecountrypicker.utils.Utils

class CountryPickerViewModel : ViewModel() {

    private val _isCountryPickerDialogVisible = mutableStateOf(value = false)
    val isCountryPickerDialogVisible : State<Boolean> = _isCountryPickerDialogVisible

    val countryList = Utils.countryList

    private val _selectedCountry = MutableLiveData(countryList[0])
    val selectedCountry : LiveData<CountryData> = _selectedCountry


    fun setCountryPickerDialogVisibility() {
        _isCountryPickerDialogVisible.value = !_isCountryPickerDialogVisible.value
    }

    fun setSelectedCountry(item : CountryData) {
        _selectedCountry.value = item
    }


}