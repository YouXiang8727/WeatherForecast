package com.youxiang8727.feature_weather.data.repository

import com.youxiang8727.core.api.ApiResult
import com.youxiang8727.core.api.fetchApi
import com.youxiang8727.feature_weather.BuildConfig
import com.youxiang8727.feature_weather.data.ApiService
import com.youxiang8727.feature_weather.data.model.toWeatherDescription
import com.youxiang8727.feature_weather.domain.model.WeatherDescription
import com.youxiang8727.feature_weather.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiService: ApiService
): ApiRepository {
    override suspend fun getWeatherDescription(
        url: String,
        timeFrom: String?,
        timeTo: String?
    ): ApiResult<WeatherDescription> {
        val apiResult = fetchApi {
            apiService.getWeatherDescription(
                url = url,
                timeFrom = timeFrom,
                timeTo = timeTo,
                elementName = "天氣預報綜合描述",
                authorization = BuildConfig.API_KEY
            )
        }

        return when (apiResult) {
            is ApiResult.Success -> {
                ApiResult.Success(apiResult.data?.toWeatherDescription())
            }

            is ApiResult.Error -> {
                ApiResult.Error(apiResult.exception)
            }
        }
    }
}