
package com.example.corpaliv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "solicitudes")
data class Solicitud(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val aporte: Int,
    val fecha: String,
    val recibirNovedades: Boolean,
    val anonima: Boolean = false,
    val rut: String = "",
    val causa: String = "Donación general",
    val apellidos: String = "",
    val aporteMensual: Boolean = false,
    val metodoPago: String = "Sin seleccionar"
)
