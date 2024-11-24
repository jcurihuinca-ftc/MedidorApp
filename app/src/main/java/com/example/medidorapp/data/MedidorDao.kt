package com.example.medidorapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MedidorDao {

    @Query("SELECT * FROM medidor_table ORDER BY fechaRegistro DESC")
    fun obtenerTodasLasMediciones(): Flow<List<MedidorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMedicion(medicion: MedidorEntity)

}
