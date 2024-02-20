package com.abi.simplecountrypicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    Column {
                        DialogCountryPicker(
                            pickedCountry = {
                                Log.d("PickedCountry", it.toString())
                            },
                            isCountryCodeVisible = true,
                            isCountryFlagVisible = true,
                            isEnabled = true,
                            dividerColor = Color.Blue
                        )
                    }

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                OutlinedTextField(value = "Hy",
                    leadingIcon = {
                        BottomSheetCountryPicker(pickedCountry = {
                            Log.d("PickedCountry2", it.toString())
                        })
                    },
                    onValueChange = { })
            }
        }
    }
}
