
package com.example.corpaliv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SolicitudDao {

    @Insert
    suspend fun insertar(solicitud: Solicitud)

    @Query("SELECT * FROM solicitudes ORDER BY id DESC")
    suspend fun obtenerTodas(): List<Solicitud>
}
