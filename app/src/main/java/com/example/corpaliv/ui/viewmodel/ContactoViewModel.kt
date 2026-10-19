package com.example.corpaliv.ui.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.corpaliv.NotificacionesLocales
import com.example.corpaliv.data.local.Contacto
import com.example.corpaliv.data.local.SolicitudDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ContactoViewModel(application: Application) : AndroidViewModel(application) {

    private val contactoDao =
        SolicitudDatabase.obtenerBaseDatos(application).contactoDao()

    var nombre by mutableStateOf("")
        private set

    var correo by mutableStateOf("")
        private set

    var organizacion by mutableStateOf("")
        private set

    var motivo by mutableStateOf("Alianza institucional")
        private set

    var mensaje by mutableStateOf("")
        private set

    var errorNombre by mutableStateOf(false)
        private set

    var errorCorreo by mutableStateOf(false)
        private set

    var errorOrganizacion by mutableStateOf(false)
        private set

    var errorMensaje by mutableStateOf(false)
        private set

    var guardando by mutableStateOf(false)
        private set

    var estado by mutableStateOf("")
        private set

    fun actualizarNombre(valor: String) {
        nombre = valor
        errorNombre = false
    }

    fun actualizarCorreo(valor: String) {
        correo = valor
        errorCorreo = false
    }

    fun actualizarOrganizacion(valor: String) {
        organizacion = valor
        errorOrganizacion = false
    }

    fun actualizarMotivo(valor: String) {
        motivo = valor
    }

    fun actualizarMensaje(valor: String) {
        mensaje = valor
        errorMensaje = false
    }

    fun validarYGuardar() {
        if (guardando) return

        errorNombre = nombre.isBlank()
        errorCorreo = !Patterns.EMAIL_ADDRESS.matcher(correo.trim()).matches()
        errorOrganizacion = organizacion.isBlank()
        errorMensaje = mensaje.isBlank()

        if (errorNombre || errorCorreo || errorOrganizacion || errorMensaje) {
            estado = "Completa correctamente todos los campos obligatorios."
            return
        }

        guardando = true
        estado = ""

        viewModelScope.launch {
            try {
                val fechaActual = SimpleDateFormat(
                    "dd/MM/yyyy HH:mm",
                    Locale.getDefault()
                ).format(Date())

                val contacto = Contacto(
                    nombre = nombre.trim(),
                    correo = correo.trim(),
                    organizacion = organizacion.trim(),
                    motivo = motivo,
                    mensaje = mensaje.trim(),
                    fechaCreacion = fechaActual
                )

                contactoDao.insertar(contacto)

                // La notificación se intenta enviar después de guardar correctamente.
                NotificacionesLocales.enviar(
                    context = getApplication(),
                    titulo = "¡Consulta registrada! 💚",
                    mensaje = "Tu consulta quedó guardada en este dispositivo. Gracias por contactar a CORPALIV."
                )

                nombre = ""
                correo = ""
                organizacion = ""
                motivo = "Alianza institucional"
                mensaje = ""

                estado = "¡Consulta guardada correctamente en este dispositivo!"
            } catch (e: Exception) {
                estado = "No se pudo guardar la consulta. Inténtalo nuevamente."
            } finally {
                guardando = false
            }
        }
    }
}
