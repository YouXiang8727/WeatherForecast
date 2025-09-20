package com.youxiang8727.feature_weather.data.model

import com.squareup.moshi.Json
import com.youxiang8727.feature_weather.domain.model.Description
import com.youxiang8727.feature_weather.domain.model.WeatherDescription
import java.time.format.DateTimeFormatter

data class WeatherDescriptionResponseBody(
    @Json(name = "success")
    val success: String,
    @Json(name = "result")
    val result: Result,
    @Json(name = "records")
    val records: Records
)

data class Result(
    @Json(name = "resource_id")
    val resourceId: String,
    @Json(name = "fields")
    val fields: List<Field>
)

data class Field(
    @Json(name = "id")
    val id: String,
    @Json(name = "type")
    val type: String
)

data class Records(
    @Json(name = "Locations")
    val locations: List<Locations>
)

data class Locations(
    @Json(name = "DatasetDescription")
    val datasetDescription: String,
    @Json(name = "LocationsName")
    val locationsName: String,
    @Json(name = "Dataid")
    val dataid: String,
    @Json(name = "Location")
    val location: List<Location>
)

data class Location(
    @Json(name = "LocationName")
    val locationName: String,
    @Json(name = "Geocode")
    val geocode: String,
    @Json(name = "Latitude")
    val latitude: String,
    @Json(name = "Longitude")
    val longitude: String,
    @Json(name = "WeatherElement")
    val weatherElement: List<WeatherElement>
)

data class WeatherElement(
    @Json(name = "ElementName")
    val elementName: String,
    @Json(name = "Time")
    val time: List<Time>
)

data class Time(
    @Json(name = "StartTime")
    val startTime: String,
    @Json(name = "EndTime")
    val endTime: String,
    @Json(name = "ElementValue")
    val elementValue: List<ElementValue>
)

data class ElementValue(
    @Json(name = "WeatherDescription")
    val weatherDescription: String
)

fun WeatherDescriptionResponseBody.toWeatherDescription(): WeatherDescription {
    return WeatherDescription(
        locations = this.records.locations.flatMap { location ->
            location.location
        }.map { location ->
            com.youxiang8727.feature_weather.domain.model.Location (
                locationName = location.locationName,
                descriptions = location.weatherElement.flatMap { weatherElement ->
                    weatherElement.time
                }.map { time ->
                    val sourceTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
                    val targetTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    Description(
                        startTime = targetTimeFormatter.format(sourceTimeFormatter.parse(time.startTime)),
                        endTime = targetTimeFormatter.format(sourceTimeFormatter.parse(time.endTime)),
                        weatherDescription = time.elementValue.first().weatherDescription
                    )
                },
                expanded = false
            )
        }
    )
}


