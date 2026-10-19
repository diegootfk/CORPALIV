
package com.example.corpaliv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactoDao {

    @Insert
    suspend fun insertar(contacto: Contacto)

    @Query("SELECT * FROM contactos ORDER BY id DESC")
    suspend fun obtenerTodos(): List<Contacto>
}
