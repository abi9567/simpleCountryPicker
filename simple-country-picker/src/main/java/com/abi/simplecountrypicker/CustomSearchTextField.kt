package com.abi.simplecountrypicker

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction

@Composable
fun CustomSearchTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    outlinedTextFieldDefaults: TextFieldColors
) {

    var searchInput by remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = searchInput,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_country))
        },
        colors = outlinedTextFieldDefaults,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        onValueChange = {
            if (it == " ") return@OutlinedTextField
            onValueChange(it)
            searchInput = it
        }
    )
}