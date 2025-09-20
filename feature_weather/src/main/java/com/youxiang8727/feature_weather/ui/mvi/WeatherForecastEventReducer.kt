package com.youxiang8727.feature_weather.ui.mvi

import com.youxiang8727.core.mvi.Reducer
import com.youxiang8727.feature_weather.domain.model.toUiState

class WeatherForecastEventReducer: Reducer<WeatherForecastUiState, WeatherForecastUiEvent>() {
    override fun reduce(
        state: WeatherForecastUiState,
        event: WeatherForecastUiEvent
    ): WeatherForecastUiState {
        return when (event) {
            is WeatherForecastUiEvent.Error -> {
                state.copy(
                    isLoading = false,
                    error = event.error
                )
            }

            WeatherForecastUiEvent.Loading -> {
                state.copy(
                    isLoading = true
                )
            }

            is WeatherForecastUiEvent.SelectCity -> {
                state.copy(
                    city = event.city,
                    showCityPicker = false
                )
            }

            is WeatherForecastUiEvent.SelectRange -> {
                state.copy(
                    range = event.range
                )
            }

            WeatherForecastUiEvent.ShowCityPicker -> {
                state.copy(
                    showCityPicker = true
                )
            }

            WeatherForecastUiEvent.CloseCityPicker -> {
                state.copy(
                    showCityPicker = false
                )
            }

            is WeatherForecastUiEvent.UpdateWeatherDescription -> {
                state.copy(
                    isLoading = false,
                    weatherDescription = event.weatherDescription.toUiState()
                )
            }

            is WeatherForecastUiEvent.ExpandLocation -> {
                val expand = state.weatherDescription[event.location]!!.isExpand.not()
                val map = state.weatherDescription.toMutableMap()
                map[event.location] = map[event.location]!!.copy(isExpand = expand)
                state.copy(
                    weatherDescription = map
                )
            }
        }
    }
}