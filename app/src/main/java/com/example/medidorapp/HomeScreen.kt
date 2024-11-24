package com.example.medidorapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medidorapp.R

@Composable
fun HomeScreen(
    onRegisterClick: () -> Unit,
    onViewListClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onRegisterClick, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.agregar_medicion))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onViewListClick, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.measurement_list))
        }
    }
}
