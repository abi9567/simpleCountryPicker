package com.abi.simplecountrypicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var textFieldValue by rememberSaveable { mutableStateOf(value = "") }
            val focusManager = LocalFocusManager.current

            Column(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = "Country Picker Dialog View", fontWeight = W700)
                Spacer(modifier = Modifier.height(height = 16.dp))
                OutlinedTextField(value = textFieldValue,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    leadingIcon = {

                        //Use this for Country Code Dialog Picker
                        DialogCountryPicker(
                            pickedCountry = {
                                Log.d("PickedCountry", it.toString())
                            },
                            isCountryCodeVisible = false,
                        )


                    }, onValueChange = { textFieldValue = it })

                Spacer(modifier = Modifier.height(height = 36.dp))


                Text(text = "Country Picker Bottom Sheet View", fontWeight = W700)
                Spacer(modifier = Modifier.height(height = 16.dp))
                OutlinedTextField(value = textFieldValue,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    leadingIcon = {

                        //Use this for Country Code Bottom Sheet Picker
                        BottomSheetCountryPicker(pickedCountry = {
                            Log.d("PickedCountry2", it.toString())
                        })


                    }, onValueChange = { textFieldValue = it })
            }
        }
    }
}
