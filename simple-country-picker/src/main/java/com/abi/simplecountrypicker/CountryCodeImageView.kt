package com.abi.simplecountrypicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abi.simplecountrypicker.data.CountryData

@Composable
internal fun CountryCodeImageView(
    modifier: Modifier,
    isEnabled: Boolean,
    isCircleShapeFlag: Boolean,
    onItemClick : () -> Unit,
    isCountryFlagVisible: Boolean,
    selectedCountry: CountryData?,
    isCountryCodeVisible: Boolean,
    countryCodeTextColorAndIconColor: Color,
    trailingIconComposable: (@Composable () -> Unit)? = null
) {

    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(shape = RoundedCornerShape(size = 4.dp))
            .clickable(enabled = isEnabled, onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isCountryFlagVisible) {
            selectedCountry?.countryIcon?.let {
                Image(
                    painter = painterResource(id = it),
                    modifier = Modifier
                        .clip(
                            shape = if (isCircleShapeFlag) CircleShape else RoundedCornerShape(
                                size = 0.dp
                            )
                        ),
                    contentDescription = null
                )
            }
        }

        if (isCountryCodeVisible) {
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(text = selectedCountry?.countryCode ?: "",
                color = countryCodeTextColorAndIconColor)
            Spacer(modifier = Modifier.width(width = 2.dp))
        }

        if (trailingIconComposable != null) {
            trailingIconComposable()
        } else {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = countryCodeTextColorAndIconColor,
                contentDescription = null
            )
        }
    }
}