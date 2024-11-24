package com.example.medidorapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.medidorapp.data.MedidorDatabase
import com.example.medidorapp.data.MedidorEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MedidorViewModel(application: Application) : AndroidViewModel(application) {

    private val medidorDao = MedidorDatabase.getDatabase(application).medidorDao()
    val listaMediciones: LiveData<List<MedidorEntity>> = medidorDao.obtenerTodasLasMediciones().asLiveData()

    fun agregarMedicion(nroMedidor: String, fechaRegistro: String, tipoMedicion: String, valorMedicion: Double) {
        viewModelScope.launch {
            try {
                val medidor = MedidorEntity(
                    nroMedidor = nroMedidor,
                    fechaRegistro = fechaRegistro,
                    tipoMedicion = tipoMedicion,
                    valorMedicion = valorMedicion
                )
                medidorDao.insertarMedicion(medidor)

                medidorDao.obtenerTodasLasMediciones().collect { allMediciones ->
                    Log.d("MedidorApp", "Mediciones in DB after insert: ${allMediciones.size}")
                    allMediciones.forEach { med ->
                        Log.d("MedidorApp", "Medidor: ${med.nroMedidor}, Fecha: ${med.fechaRegistro}, Tipo: ${med.tipoMedicion}, Valor: ${med.valorMedicion}")
                    }
                }
            } catch (e: Exception) {
                Log.e("MedidorApp", "Error al guardar en la base de datos: ${e.localizedMessage ?: e.toString()}")
            }
        }
    }
}
