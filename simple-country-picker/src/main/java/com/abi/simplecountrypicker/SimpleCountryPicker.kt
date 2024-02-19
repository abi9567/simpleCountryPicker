package com.abi.simplecountrypicker

import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
* @param defaultCountryIdentifier for set initial country code and flag visible in the composable view. If not specifier the default country code shown will be India.
 * for full list of available [defaultCountryIdentifier]
 * check here country_code_identifiers.md in the git repo.
* @param isCountryCodeVisible set true or false for country code visibility in the composable view. The default value is true.
* @param isCountryFlagVisible set true or false for country flag visibility in the composable view. The default value is true.
* @param isEnabled set true for this view clickable. set false for not clickable. The default value is true.
* @param trailingIconComposable you can also change trailing icon by using this parameter - default it's null. If specify [trailingIconComposable] the default down arrow icon will not be visible, and this [trailingIconComposable] will be shown
*/

@Composable
fun SimpleCountryPicker(modifier : Modifier = Modifier,
                        defaultCountryIdentifier : String = "in",
                        isCountryCodeVisible : Boolean = true,
                        isCountryFlagVisible : Boolean = true,
                        isEnabled : Boolean = true,
                        trailingIconComposable : (@Composable () -> Unit)? = null,
                        viewModel : CountryPickerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val isCountryPickerDialogVisible = viewModel.isCountryPickerDialogVisible.value
    val countryList = viewModel.countryList.observeAsState().value
    val selectedCountry = viewModel.selectedCountry.observeAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.setInitialCountry(countryIdentifier = defaultCountryIdentifier)
    }

    if (isCountryPickerDialogVisible) {
        CountryPickerDialog(
            countryList = countryList,
            onItemClick = { country ->
                viewModel.setSelectedCountry(item = country)
                viewModel.setCountryPickerDialogVisibility()
                viewModel.resetCountryList()
            },
            onSearch = {
                viewModel.searchCountry(searchQuery = it, context = context)},
            onDismiss = {
                viewModel.setCountryPickerDialogVisibility()
                viewModel.resetCountryList()
        })
    }

    Row(modifier = modifier
        .wrapContentSize()
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .clip(shape = RoundedCornerShape(size = 4.dp))
        .clickable(enabled = isEnabled, onClick = { viewModel.setCountryPickerDialogVisibility() }),
        verticalAlignment = Alignment.CenterVertically) {

        if (isCountryFlagVisible) {
            Image(painter = painterResource(id = selectedCountry?.countryIcon ?: 0),
                contentDescription = null)
            Spacer(modifier = Modifier.width(width = 8.dp))
        }

        if (isCountryCodeVisible) {
            Text(text = selectedCountry?.countryCode ?: "")
            Spacer(modifier = Modifier.width(width = 4.dp))
        }

        if (trailingIconComposable != null) { trailingIconComposable() }
        else { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null) }
    }
}