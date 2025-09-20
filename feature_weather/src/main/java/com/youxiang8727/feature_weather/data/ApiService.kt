package com.youxiang8727.feature_weather.data

import com.youxiang8727.feature_weather.data.model.WeatherDescriptionResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getWeatherDescription(
        @Url url: String,
        @Query("Authorization") authorization: String,
        @Query("ElementName") elementName: String,
        @Query("timeFrom") timeFrom: String?,
        @Query("timeTo") timeTo: String?
    ): Response<WeatherDescriptionResponseBody>
}