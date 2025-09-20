package com.youxiang8727.feature_weather.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youxiang8727.feature_weather.ui.mvi.WeatherForecastRange

@Composable
fun ForecastScreenHeader(
    modifier: Modifier = Modifier,
    city: String,
    showCityPicker: () -> Unit,
    range: WeatherForecastRange,
    onRangeSelected: (WeatherForecastRange) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = city,
            style = MaterialTheme.typography.titleLarge
        )

        IconButton(
            onClick = {
                showCityPicker()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        WeatherForecastRange.entries.forEach { _range ->
            FilterChip(
                selected = _range == range,
                onClick = {
                    onRangeSelected(_range)
                },
                label = {
                    Text(
                        text = _range.rangeName
                    )
                }
            )
        }
    }
}