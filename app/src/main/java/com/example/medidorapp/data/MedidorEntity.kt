package com.example.medidorapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medidor_table")
data class MedidorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nroMedidor: String,
    val fechaRegistro: String,
    val tipoMedicion: String,
    val valorMedicion: Double
)
