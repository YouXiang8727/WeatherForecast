package com.youxiang8727.feature_weather.ui.mvi

import com.youxiang8727.core.mvi.UiState
import com.youxiang8727.feature_weather.data.model.cityEndPoints
import com.youxiang8727.feature_weather.domain.model.Location
import com.youxiang8727.feature_weather.domain.model.WeatherDescription
import com.youxiang8727.feature_weather.domain.model.toUiState

data class WeatherForecastUiState(
    val range: WeatherForecastRange = WeatherForecastRange.TODAY,
    val city: String = cityEndPoints.keys.first(),
    val showCityPicker: Boolean = false,
    val isLoading: Boolean = false,
    val weatherDescription: Map<String, WeatherDescriptionUiState> = WeatherDescription().toUiState(),
    val error: String = "",
): UiState

data class WeatherDescriptionUiState(
    val locations: List<Location> = emptyList(),
    val isExpand: Boolean = false
)

enum class WeatherForecastRange(
    val rangeName: String
) {
    TODAY("本日"), WEEKLY("本週")
}