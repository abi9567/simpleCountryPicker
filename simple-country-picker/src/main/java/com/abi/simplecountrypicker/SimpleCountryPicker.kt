package com.abi.simplecountrypicker

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.abi.simplecountrypicker.data.CountryData
import com.abi.simplecountrypicker.theme.SearchFieldBackgroundColor

/**
 * @param pickedCountry Returns the picked [CountryData]. You can use [CountryData.countryCode], [CountryData.countryIcon], [CountryData.countryName] from it. Where [CountryData.countryName] is @StringRes. Use [context.getString] to retrieve the string value.
 * @param defaultCountryIdentifier for set initial country code and flag visible in the composable view. If not specifier the default country code shown will be India.
 * for full list of available [defaultCountryIdentifier] check here [countryCodeIdentifiers](https://github.com/abi9567/simpleCountryPicker/blob/main/country_code_identifiers.md)
 * @param dialogTitle the title of the country list dialog view. Default value is "Select Country".
 * @param isCountryCodeVisible set true or false for country code visibility in the composable view. The default value is true.
 * @param isCountryFlagVisible set true or false for country flag visibility in the composable view. The default value is true.
 * @param isCircleShapeFlag Whether the flag shown in circle shape or rectangular. Default value is true (circle shape).
 * @param isEnabled set true for this view clickable. set false for not clickable. The default value is true.
 * @param trailingIconComposable you can also change trailing icon by using this parameter - default it's null. If specify [trailingIconComposable] the default down arrow icon will not be visible, and this [trailingIconComposable] will be shown
 * @param backgroundColor background color of the dialog. Default [backgroundColor] is White.
 * @param textColor textColor of the country name & country code in the dialog. Default [textColor] is Black.
 * @param countryCodeTextColorAndIconColor color of the country code & dropdown icon. Default [textColor] is Black.
 * @param stickyHeaderBackgroundColor background color of the sticky header. Default [backgroundColor] is White.
 * @param dividerColor used for the divider color of dialog. It's default value is [DividerDefaults.color]
 * @param searchFieldColors is used for change the outlined search field colors it's type is [TextFieldColors] you can use [OutlinedTextFieldDefaults.colors] property to customize it's colors.
 */

@Composable
fun DialogCountryPicker(
    modifier: Modifier = Modifier,
    pickedCountry: (CountryData) -> Unit,
    defaultCountryIdentifier: String = "in",
    dialogTitle: String = "Select Country",
    isCountryCodeVisible: Boolean = true,
    isCountryFlagVisible: Boolean = true,
    isCircleShapeFlag: Boolean = true,
    isEnabled: Boolean = true,
    trailingIconComposable: (@Composable () -> Unit)? = null,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    countryCodeTextColorAndIconColor: Color = Color.Black,
    stickyHeaderBackgroundColor: Color = SearchFieldBackgroundColor,
    dividerColor: Color = DividerDefaults.color,
    searchFieldColors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedContainerColor = SearchFieldBackgroundColor,
        focusedContainerColor = SearchFieldBackgroundColor,
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent
    )
) {
    val viewModel: CountryPickerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val isCountryPickerDialogVisible = viewModel.isCountryPickerDialogVisible.value
    val countryList = viewModel.countryList.observeAsState().value
    val selectedCountry = viewModel.selectedCountry.observeAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.setInitialCountry(countryIdentifier = defaultCountryIdentifier)
    }

    LaunchedEffect(key1 = selectedCountry) {
        selectedCountry?.let { pickedCountry(it) }
    }

    if (isCountryPickerDialogVisible) {
        CountryPickerDialog(
            title = dialogTitle,
            countryList = countryList,
            backgroundColor = backgroundColor,
            dividerColor = dividerColor,
            textColor = textColor,
            stickyHeaderBackgroundColor = stickyHeaderBackgroundColor,
            outlinedTextFieldDefaults = searchFieldColors,
            onItemClick = { country ->
                viewModel.setSelectedCountry(item = country)
                viewModel.setCountryPickerDialogVisibility()
                viewModel.resetCountryList()
            },
            onSearch = {
                viewModel.searchCountry(searchQuery = it, context = context)
            },
            onDismiss = {
                viewModel.setCountryPickerDialogVisibility()
                viewModel.resetCountryList()
            })
    }

    CountryCodeImageView(
        modifier = modifier,
        isEnabled = isEnabled,
        onItemClick = { viewModel.setCountryPickerDialogVisibility() },
        isCountryFlagVisible = isCountryFlagVisible,
        selectedCountry = selectedCountry,
        isCountryCodeVisible = isCountryCodeVisible,
        isCircleShapeFlag = isCircleShapeFlag,
        trailingIconComposable = trailingIconComposable,
        countryCodeTextColorAndIconColor = countryCodeTextColorAndIconColor
    )
}