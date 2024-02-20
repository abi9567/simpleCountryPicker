package com.abi.simplecountrypicker

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.abi.simplecountrypicker.data.CountryData

@Composable
internal fun CountryPickerDialog(
    onDismiss: () -> Unit,
    title: String,
    onItemClick: (CountryData) -> Unit,
    countryList: List<CountryData>?,
    onSearch: (String) -> Unit,
    backgroundColor: Color,
    stickyHeaderBackgroundColor: Color,
    dividerColor: Color,
    textColor: Color,
    outlinedTextFieldDefaults: TextFieldColors,
    dialogProperties: DialogProperties = DialogProperties()
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = dialogProperties
    ) {
        CountryListView(
            onDismiss = onDismiss,
            title = title,
            onItemClick = onItemClick,
            onSearch = onSearch,
            backgroundColor = backgroundColor,
            stickyHeaderBackgroundColor = stickyHeaderBackgroundColor,
            outlinedTextFieldDefaults = outlinedTextFieldDefaults,
            dividerColor = dividerColor,
            textColor = textColor,
            countryList = countryList
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CountryListView(
    title: String,
    onDismiss: () -> Unit,
    onSearch: (String) -> Unit,
    verticalPadding: Dp = 24.dp,
    onItemClick: (CountryData) -> Unit,
    backgroundColor: Color,
    stickyHeaderBackgroundColor: Color,
    dividerColor: Color,
    textColor: Color,
    outlinedTextFieldDefaults: TextFieldColors,
    countryList: List<CountryData>?
) {

    val groupedCountryName = countryList?.groupBy { it.countryIdentifier[0] }

    Column(
        modifier = Modifier
            .padding(vertical = verticalPadding)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .fillMaxSize()
    )
    {

        Row(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 16.sp, fontWeight = W600, color = textColor)
            Spacer(modifier = Modifier.weight(weight = 1F))
            IconButton(onClick = onDismiss) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(height = 8.dp))

        CustomSearchTextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            onValueChange = onSearch,
            outlinedTextFieldDefaults = outlinedTextFieldDefaults
        )

        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            groupedCountryName?.forEach { (firstLetter, groupedCountryList) ->
                stickyHeader {
                    Text(
                        text = "${firstLetter.uppercaseChar()}",
                        fontWeight = W600,
                        modifier = Modifier
                            .background(color = stickyHeaderBackgroundColor)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                items(groupedCountryList) { item ->
                    CountryListSingleItemView(
                        modifier = Modifier.clickable { onItemClick(item) },
                        dividerColor = dividerColor,
                        isDividerVisible = groupedCountryList.last() != item,
                        textColor = textColor,
                        countryData = item
                    )
                }
            }
        }
    }
}

@Composable
private fun CountryListSingleItemView(
    modifier: Modifier = Modifier,
    dividerColor: Color,
    textColor: Color,
    isDividerVisible: Boolean,
    countryData: CountryData
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .height(intrinsicSize = IntrinsicSize.Max)
                .padding(vertical = 13.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

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
                    .width(width = 0.9.dp),
                color = dividerColor
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp,
                            fontWeight = W600
                        )
                    ) {
                        append(text = "(${countryData.countryCode})  ")
                    }

                    withStyle(style = SpanStyle(fontSize = 14.sp)) {
                        append(text = stringResource(id = countryData.countryName))
                    }

                }, maxLines = 2,
                color = textColor,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (isDividerVisible) {
            HorizontalDivider(thickness = 0.65.dp, color = dividerColor)
        }
    }
}
