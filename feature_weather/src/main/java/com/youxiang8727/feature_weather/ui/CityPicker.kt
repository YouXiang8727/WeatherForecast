package com.youxiang8727.feature_weather.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.youxiang8727.feature_weather.data.model.cityEndPoints

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityPicker(
    modifier: Modifier = Modifier,
    currentCity: String,
    onCitySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedCity by remember {
        mutableStateOf(currentCity)
    }

    var filterText by remember {
        mutableStateOf("")
    }

    BasicAlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = filterText,
                onValueChange = {
                    filterText = it
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            filterText = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text("輸入縣市名稱")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "目前選擇:$currentCity"
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f)
            ) {
                items(
                    cityEndPoints.keys.toList().filter {
                        it.contains(filterText)
                    }
                ) {
                    CityPickerItem(
                        modifier = Modifier.fillMaxWidth(),
                        city = it,
                        isSelected = it == selectedCity,
                        onClick = {
                            selectedCity = it
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End)
            ) {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("取消")
                }

                Button(
                    onClick = {
                        onCitySelected(selectedCity)
                    }
                ) {
                    Text("確定")
                }
            }
        }
    }
}

@Composable
fun CityPickerItem(
    modifier: Modifier = Modifier,
    city: String,
    isSelected: Boolean,
    onClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .clickable {
                onClick(city)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = city
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null
            )
        }
    }
}