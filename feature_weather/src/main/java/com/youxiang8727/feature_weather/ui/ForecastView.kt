package com.youxiang8727.feature_weather.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youxiang8727.feature_weather.domain.model.Description
import com.youxiang8727.feature_weather.ui.mvi.WeatherDescriptionUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastView(
    modifier: Modifier = Modifier,
    weatherDescription: Map<String, WeatherDescriptionUiState>,
    expand: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            weatherDescription.forEach { (location, uiState) ->
                stickyHeader {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .clickable {
                                expand(location)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = location,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

                        Icon(
                            imageVector = if (uiState.isExpand) {
                                Icons.Default.KeyboardArrowUp
                            } else {
                                Icons.Default.KeyboardArrowDown
                            },
                            contentDescription = null
                        )
                    }
                }

                if (uiState.isExpand) {
                    items(uiState.locations.flatMap { it.descriptions }) { description ->
                        ForecastItem(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 8.dp),
                            description = description
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    description: Description
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "${description.startTime} ~ ${description.endTime}",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = description.weatherDescription,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
