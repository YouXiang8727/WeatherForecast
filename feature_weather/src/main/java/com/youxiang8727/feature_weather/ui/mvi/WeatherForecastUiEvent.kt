package com.youxiang8727.feature_weather.ui.mvi

import com.youxiang8727.core.mvi.UiEvent
import com.youxiang8727.feature_weather.domain.model.WeatherDescription

sealed interface WeatherForecastUiEvent: UiEvent {
    data object Loading: WeatherForecastUiEvent

    data class SelectRange(val range: WeatherForecastRange): WeatherForecastUiEvent

    data class SelectCity(val city: String): WeatherForecastUiEvent

    data object ShowCityPicker: WeatherForecastUiEvent

    data object CloseCityPicker: WeatherForecastUiEvent

    data class Error(val error: String): WeatherForecastUiEvent

    data class UpdateWeatherDescription(val weatherDescription: WeatherDescription): WeatherForecastUiEvent

    data class ExpandLocation(val location: String): WeatherForecastUiEvent
}