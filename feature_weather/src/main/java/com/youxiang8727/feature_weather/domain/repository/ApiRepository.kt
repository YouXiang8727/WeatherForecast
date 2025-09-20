package com.youxiang8727.feature_weather.domain.repository

import com.youxiang8727.core.api.ApiResult
import com.youxiang8727.feature_weather.domain.model.WeatherDescription

interface ApiRepository {
    suspend fun getWeatherDescription(
        url: String,
        timeFrom: String?,
        timeTo: String?
    ): ApiResult<WeatherDescription>
}