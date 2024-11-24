package com.example.medidorapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medidorapp.viewmodel.MedidorViewModel
import com.example.medidorapp.R
import android.util.Log
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicionesListScreen(
    viewModel: MedidorViewModel,
    onBackClick: () -> Unit
) {
    val mediciones = viewModel.listaMediciones.observeAsState(initial = emptyList()).value
    Log.d("MedidorApp", "Mediciones retrieved: ${mediciones.size}")

    LaunchedEffect(mediciones) {
        Log.d("MedidorApp", "Observed mediciones: ${mediciones.size}")
        mediciones.forEach { medicion ->
            Log.d(
                "MedidorApp",
                "Medidor: ${medicion.nroMedidor}, Fecha: ${medicion.fechaRegistro}, Tipo: ${medicion.tipoMedicion}, Valor: ${medicion.valorMedicion}"
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.measurement_list)) })
        },
        bottomBar = {
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(stringResource(id = R.string.volver))
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (mediciones.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.no_measurements),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                items(mediciones.sortedBy { it.nroMedidor }) { medicion ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Medidor: ${medicion.nroMedidor}")
                        Text("Fecha: ${medicion.fechaRegistro}")
                        Text("Tipo: ${medicion.tipoMedicion}")
                        Text("Valor: ${medicion.valorMedicion}")
                    }
                    Divider()
                }
            }
        }
    }
}
