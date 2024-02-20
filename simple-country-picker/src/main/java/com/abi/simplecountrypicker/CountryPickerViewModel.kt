package com.abi.simplecountrypicker

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abi.simplecountrypicker.data.CountryData
import com.abi.simplecountrypicker.utils.Utils
import java.util.Locale


class CountryPickerViewModel : ViewModel() {

    private val _isCountryPickerDialogVisible = mutableStateOf(value = false)
    val isCountryPickerDialogVisible: State<Boolean> = _isCountryPickerDialogVisible

    private val _isBottomSheetVisible = mutableStateOf(value = false)
    val isBottomSheetVisible: State<Boolean> = _isBottomSheetVisible

    private val fullCountryList = Utils.countryList

    private val _countryList = MutableLiveData(fullCountryList)
    val countryList: LiveData<List<CountryData>> = _countryList

    private val _selectedCountry = MutableLiveData<CountryData?>(null)
    val selectedCountry: LiveData<CountryData?> = _selectedCountry

    fun setInitialCountry(countryIdentifier: String) {
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

    fun setBottomSheetVisibility() {
        _isBottomSheetVisible.value = !_isBottomSheetVisible.value
    }

    fun setSelectedCountry(item: CountryData) {
        _selectedCountry.value = item
    }

    fun resetCountryList() {
        _countryList.value = fullCountryList
    }

    fun searchCountry(searchQuery: String, context: Context) {
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