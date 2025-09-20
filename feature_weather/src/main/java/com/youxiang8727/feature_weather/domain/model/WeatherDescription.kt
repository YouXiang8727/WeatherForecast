package com.youxiang8727.feature_weather.domain.model

import com.youxiang8727.feature_weather.ui.mvi.WeatherDescriptionUiState

data class WeatherDescription(
    val locations: List<Location> = emptyList()
)

data class Location(
    val locationName: String,
    val descriptions: List<Description>,
    val expanded: Boolean
)

data class Description(
    val startTime: String,
    val endTime: String,
    val weatherDescription: String
)

fun WeatherDescription.toUiState(): Map<String, WeatherDescriptionUiState> {
    return this.locations.groupBy {
        it.locationName
    }.map { (locationName, descriptions) ->
        locationName to WeatherDescriptionUiState(descriptions, false)
    }.toMap()
}
