package com.youxiang8727.feature_weather.domain.usecase

import com.youxiang8727.core.api.ApiResult
import com.youxiang8727.feature_weather.domain.model.WeatherDescription
import com.youxiang8727.feature_weather.domain.repository.ApiRepository
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetTodayWeatherForecastUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    suspend operator fun invoke(
        url: String
    ): ApiResult<WeatherDescription> {
        val today = LocalDate.now()
        val timeFrom = today.atStartOfDay().format(formatter)
        val timeTo = today.atTime(LocalTime.MAX).format(formatter)

        return apiRepository.getWeatherDescription(
            url = url,
            timeFrom = timeFrom,
            timeTo = timeTo
        )
    }
}