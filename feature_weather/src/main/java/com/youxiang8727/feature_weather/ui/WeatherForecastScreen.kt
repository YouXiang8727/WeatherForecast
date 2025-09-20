package com.youxiang8727.feature_weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youxiang8727.feature_weather.ui.mvi.WeatherForecastViewModel

@Composable
fun WeatherForecastScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherForecastViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ForecastScreenHeader(
                modifier = Modifier.fillMaxWidth(),
                city = state.value.city,
                showCityPicker = {
                    viewModel.showCityPicker()
                },
                range = state.value.range,
                onRangeSelected = { range ->
                    viewModel.selectForecastRange(range)
                }
            )

            Box(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (state.value.isLoading) {
                    CircularProgressIndicator()
                } else if (state.value.error.isEmpty()) {
                    ForecastView(
                        modifier = Modifier.fillMaxSize(),
                        weatherDescription = state.value.weatherDescription,
                        expand = { location ->
                            viewModel.expandLocation(location)
                        }
                    )
                } else {
                    ErrorView(
                        error = state.value.error,
                        retry = {
                            viewModel.getWeatherForecast()
                        }
                    )
                }
            }
        }

        if (state.value.showCityPicker) {
            CityPicker(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background),
                currentCity = state.value.city,
                onCitySelected = { city ->
                    viewModel.selectCity(city)
                },
                onDismiss = {
                    viewModel.closeCityPicker()
                }
            )
        }
    }
}