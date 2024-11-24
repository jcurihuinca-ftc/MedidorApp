package com.example.medidorapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medidorapp.viewmodel.MedidorViewModel
import com.example.medidorapp.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioMedicionScreen(
    viewModel: MedidorViewModel,
    onBackClick: () -> Unit
) {
    var nroMedidor by remember { mutableStateOf("") }
    var fechaRegistro by remember { mutableStateOf("") }
    var valorMedicion by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Agua") }
    val snackbarHostState = remember { SnackbarHostState() }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var latestMeasurement by remember { mutableStateOf<String?>(null) }

    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.agregar_medicion)) }) },
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
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            TextField(
                value = nroMedidor,
                onValueChange = { nroMedidor = it },
                label = { Text("Número de Medidor") },
                placeholder = { Text("Ej: 123") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = fechaRegistro,
                onValueChange = { fechaRegistro = it },
                label = { Text("Fecha de Registro") },
                placeholder = { Text("Formato: dd-MM-yyyy") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text("Medidor de:")
            Column {
                listOf("Agua", "Luz", "Gas").forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = valorMedicion,
                onValueChange = { valorMedicion = it.filter { char -> char.isDigit() || char == '.' } },
                label = { Text("Valor de Medición") },
                placeholder = { Text("Ej: ${currencyFormat.format(24000)}") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val isDateValid = runCatching { dateFormat.parse(fechaRegistro) }.isSuccess
                    val valor = valorMedicion.toDoubleOrNull()
                    if (isDateValid && valor != null) {
                        viewModel.agregarMedicion(
                            nroMedidor = nroMedidor,
                            fechaRegistro = fechaRegistro,
                            tipoMedicion = selectedOption,
                            valorMedicion = valor
                        )

                        latestMeasurement = "Medidor: $nroMedidor, Fecha: $fechaRegistro, $selectedOption: ${currencyFormat.format(valor)}"
                        nroMedidor = ""
                        fechaRegistro = ""
                        valorMedicion = ""

                        scope.launch {
                            snackbarHostState.showSnackbar("Medición guardada correctamente: $latestMeasurement")
                        }
                    } else {
                        errorMessage = "Formato incorrecto: Verifique la fecha o valor ingresado."

                        scope.launch {
                            snackbarHostState.showSnackbar(errorMessage!!)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.guardar))
            }

            latestMeasurement?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Última Medición Guardada:")
                Text(it)
            }
        }
    }
}
