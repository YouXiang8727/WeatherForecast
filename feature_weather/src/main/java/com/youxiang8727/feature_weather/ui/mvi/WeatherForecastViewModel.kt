package com.youxiang8727.feature_weather.ui.mvi

import androidx.lifecycle.viewModelScope
import com.youxiang8727.core.api.ApiResult
import com.youxiang8727.core.mvi.MviViewModel
import com.youxiang8727.feature_weather.data.model.cityEndPoints
import com.youxiang8727.feature_weather.domain.model.WeatherDescription
import com.youxiang8727.feature_weather.domain.usecase.GetTodayWeatherForecastUseCase
import com.youxiang8727.feature_weather.domain.usecase.GetWeeklyWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getTodayWeatherForecastUseCase: GetTodayWeatherForecastUseCase,
    private val getWeeklyWeatherForecastUseCase: GetWeeklyWeatherForecastUseCase
): MviViewModel<WeatherForecastUiState, WeatherForecastUiEvent>(
    initialState = WeatherForecastUiState(),
    reducer = WeatherForecastEventReducer()
) {
    private var getWeatherForecastJob: Job? = null

    fun selectForecastRange(
        range: WeatherForecastRange
    ) {
        getWeatherForecastJob?.cancel()
        dispatch(WeatherForecastUiEvent.SelectRange(range))
        getWeatherForecast()
    }

    fun showCityPicker() {
        getWeatherForecastJob?.cancel()
        dispatch(
            WeatherForecastUiEvent.ShowCityPicker
        )
    }

    fun selectCity(city: String) {
        dispatch(
            WeatherForecastUiEvent.SelectCity(city)
        )
        getWeatherForecast()
    }

    fun closeCityPicker() {
        dispatch(
            WeatherForecastUiEvent.CloseCityPicker
        )
    }

    fun expandLocation(location: String) {
        dispatch(
            WeatherForecastUiEvent.ExpandLocation(location)
        )
    }

    fun getWeatherForecast() {
        getWeatherForecastJob = viewModelScope.launch {
            dispatch(
                WeatherForecastUiEvent.Loading
            )

            val endPoints = cityEndPoints[state.value.city]
            val apiResult = when (state.value.range) {
                WeatherForecastRange.TODAY -> getTodayWeatherForecastUseCase(
                    endPoints!!.threeDays
                )
                WeatherForecastRange.WEEKLY -> getWeeklyWeatherForecastUseCase(
                    endPoints!!.week
                )
            }

            when (apiResult) {
                is ApiResult.Error -> {
                    dispatch(
                        WeatherForecastUiEvent.Error(apiResult.exception.message.toString())
                    )
                }
                is ApiResult.Success<WeatherDescription> -> {
                    dispatch(
                        WeatherForecastUiEvent.UpdateWeatherDescription(apiResult.data!!)
                    )
                }
            }
        }
    }

    init {
        getWeatherForecast()
    }
}