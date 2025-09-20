package com.youxiang8727.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youxiang8727.feature_weather.ui.WeatherForecastScreen
import com.youxiang8727.weatherforecast.ui.theme.WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherForecastScreen(
                        modifier = Modifier.fillMaxSize()
                            .padding(
                                start = 8.dp,
                                top = innerPadding.calculateTopPadding(),
                                end = 8.dp,
                                bottom = innerPadding.calculateBottomPadding()
                            )
                    )
                }
            }
        }
    }
}
