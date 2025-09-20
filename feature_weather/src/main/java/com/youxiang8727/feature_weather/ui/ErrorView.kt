package com.youxiang8727.feature_weather.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    error: String = "",
    retry: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = error
        )

        Button(
            onClick = {
                retry()
            }
        ) {
            Text("重試")
        }
    }
}