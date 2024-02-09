package com.abi.simplecountrypicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.abi.simplecountrypicker.data.CountryData

@Composable
fun CountryPickerDialog(onDismiss : () -> Unit,
                        onItemClick : (CountryData) -> Unit,
                        countryList : List<CountryData>,
                        dialogProperties: DialogProperties = DialogProperties()
) {

    Dialog(onDismissRequest = onDismiss,
        properties = dialogProperties
    ) {
        CountryListView(onDismiss = onDismiss,
            onItemClick =  onItemClick,
            countryList = countryList)
    }
}

@Composable
private fun CountryListView(
    onDismiss : () -> Unit,
    onItemClick : (CountryData) -> Unit,
    countryList : List<CountryData>) {

    Column(modifier = Modifier
        .padding(vertical = 24.dp)
        .background(
            color = Color.White,
            shape = RoundedCornerShape(size = 16.dp)
        )
        .fillMaxSize())
    {

        Row(modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Select Country", fontSize = 16.sp, fontWeight = W600)
            Spacer(modifier = Modifier.weight(weight = 1F))
            IconButton(onClick = { onDismiss() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(height = 8.dp))

        LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
            items(countryList) { item ->
                CountryListSingleItemView(
                    modifier = Modifier.clickable { onItemClick(item) },
                    countryData = item)
            }
        }
    }
}

@Composable
private fun CountryListSingleItemView(modifier: Modifier = Modifier,
                                      countryData: CountryData
) {

    Column(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center) {

        Row(modifier = Modifier
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(vertical = 13.dp, horizontal = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(id = countryData.countryIcon),
                modifier = Modifier
                    .shadow(
                        elevation = 3.dp,
                        clip = true,
                        shape = CircleShape
                    )
                    .clip(shape = CircleShape),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(width = 8.dp))

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width = 0.9.dp)
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(fontSize = 14.sp,
                    fontWeight = W600)) {
                    append(text = "(${ countryData.countryCode })  ") }

                withStyle(style = SpanStyle(fontSize = 14.sp)) {
                    append(text = stringResource(id = countryData.countryName)) }

            }, maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }
        HorizontalDivider(thickness = 0.65.dp)
    }
}
