package com.youxiang8727.feature_weather.domain.usecase

import com.youxiang8727.core.api.ApiResult
import com.youxiang8727.feature_weather.domain.model.WeatherDescription
import com.youxiang8727.feature_weather.domain.repository.ApiRepository
import javax.inject.Inject

class GetWeeklyWeatherForecastUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(
        url: String
    ): ApiResult<WeatherDescription> {
        return apiRepository.getWeatherDescription(
            url = url,
            timeFrom = null,
            timeTo = null
        )
    }
}