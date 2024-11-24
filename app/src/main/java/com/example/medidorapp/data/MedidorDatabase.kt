package com.example.medidorapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MedidorEntity::class], version = 2, exportSchema = false)
abstract class MedidorDatabase : RoomDatabase() {
    abstract fun medidorDao(): MedidorDao

    companion object {
        @Volatile
        private var INSTANCE: MedidorDatabase? = null

        fun getDatabase(context: Context): MedidorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedidorDatabase::class.java,
                    "medidor_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
