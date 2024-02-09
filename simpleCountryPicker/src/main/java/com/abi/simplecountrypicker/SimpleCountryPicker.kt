package com.abi.simplecountrypicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCountryPicker(modifier : Modifier = Modifier,
                        viewModel : CountryPickerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val isCountryPickerDialogVisible = viewModel.isCountryPickerDialogVisible.value
    val countryList = viewModel.countryList
    val selectedCountry = viewModel.selectedCountry.observeAsState().value

    if (isCountryPickerDialogVisible) {
        CountryPickerDialog(
            countryList = countryList,
            onItemClick = { country ->
                viewModel.setSelectedCountry(item = country)
                viewModel.setCountryPickerDialogVisibility()
            },
            onDismiss = {
                viewModel.setCountryPickerDialogVisibility()
        })
    }

    Row(modifier = modifier
        .wrapContentSize()
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .clip(shape = RoundedCornerShape(size = 4.dp))
        .clickable { viewModel.setCountryPickerDialogVisibility() },
        verticalAlignment = Alignment.CenterVertically) {

        Image(painter = painterResource(id = selectedCountry?.countryIcon ?: 0),
            contentDescription = null)
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(text = selectedCountry?.countryCode ?: "")
        Spacer(modifier = Modifier.width(width = 4.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null)
    }
}