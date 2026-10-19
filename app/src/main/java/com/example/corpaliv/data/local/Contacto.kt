
package com.example.corpaliv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class Contacto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val organizacion: String,
    val motivo: String,
    val mensaje: String,
    val fechaCreacion: String
)
