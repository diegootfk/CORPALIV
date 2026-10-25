
package com.example.corpaliv.ui.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.corpaliv.NotificacionesLocales
import com.example.corpaliv.data.local.Solicitud
import com.example.corpaliv.data.local.SolicitudDatabase
import kotlinx.coroutines.launch
import java.util.Locale

class ColaborarViewModel(application: Application) : AndroidViewModel(application) {

    private val solicitudDao =
        SolicitudDatabase.obtenerBaseDatos(application).solicitudDao()

    var nombre by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var rut by mutableStateOf("")
        private set
    var correo by mutableStateOf("")
    var aporte by mutableStateOf("")
    var fecha by mutableStateOf("")
    var causaSeleccionada by mutableStateOf("")
    var aporteMensual by mutableStateOf(false)
    var metodoPago by mutableStateOf("")

    var aceptaCondiciones by mutableStateOf(false)
    var recibirNovedades by mutableStateOf(false)
    var anonima by mutableStateOf(false)

    var errorNombre by mutableStateOf(false)
    var errorApellidos by mutableStateOf(false)
    var errorRut by mutableStateOf(false)
    var errorCorreo by mutableStateOf(false)
    var errorAporte by mutableStateOf(false)
    var errorFecha by mutableStateOf(false)
    var errorCondiciones by mutableStateOf(false)
    var errorMetodoPago by mutableStateOf(false)

    var mensaje by mutableStateOf("")
    var guardando by mutableStateOf(false)

    var solicitudes by mutableStateOf<List<Solicitud>>(emptyList())
        private set

    init {
        cargarSolicitudes()
    }

    fun seleccionarCausa(causa: String) {
        causaSeleccionada = causa
        mensaje = ""
    }

    fun volverACausas() {
        causaSeleccionada = ""
        mensaje = ""
    }

    fun actualizarRut(valor: String) {
        val limpio = valor.uppercase(Locale.ROOT)
            .filter { it.isDigit() || it == 'K' }
            .take(9)

        rut = if (limpio.length <= 1) {
            limpio
        } else {
            val cuerpo = limpio.dropLast(1)
            val digito = limpio.last()
            val cuerpoConPuntos = cuerpo.reversed()
                .chunked(3)
                .joinToString(".")
                .reversed()
            "$cuerpoConPuntos-$digito"
        }

        errorRut = false
        mensaje = ""
    }

    private fun rutEsValido(valor: String): Boolean {
        val limpio = valor.uppercase(Locale.ROOT)
            .replace(".", "")
            .replace("-", "")
            .replace(" ", "")

        if (limpio.length !in 2..9) return false

        val cuerpo = limpio.dropLast(1)
        val digitoIngresado = limpio.last()

        if (!cuerpo.all { it.isDigit() }) return false
        if (cuerpo.first() == '0') return false
        if (digitoIngresado !in '0'..'9' && digitoIngresado != 'K') {
            return false
        }

        var multiplicador = 2
        var suma = 0

        for (caracter in cuerpo.reversed()) {
            suma += caracter.digitToInt() * multiplicador
            multiplicador = if (multiplicador == 7) 2 else multiplicador + 1
        }

        val resto = 11 - (suma % 11)
        val digitoCalculado = when (resto) {
            11 -> '0'
            10 -> 'K'
            else -> resto.digitToChar()
        }

        return digitoIngresado == digitoCalculado
    }

    fun cargarSolicitudes() {
        viewModelScope.launch {
            try {
                solicitudes = solicitudDao.obtenerTodas()
            } catch (e: Exception) {
                mensaje = "No se pudieron cargar las solicitudes."
            }
        }
    }

    fun validarSolicitud(): Boolean {
        errorNombre = nombre.isBlank()
        errorApellidos = apellidos.isBlank()
        errorRut = !rutEsValido(rut)
        errorCorreo = !Patterns.EMAIL_ADDRESS.matcher(correo.trim()).matches()

        val cantidad = aporte.toIntOrNull()
        errorAporte = cantidad == null || cantidad <= 0
        errorFecha = fecha.isBlank()
        errorCondiciones = !aceptaCondiciones
        errorMetodoPago = metodoPago.isBlank()

        return causaSeleccionada.isNotBlank() &&
                !errorNombre &&
                !errorApellidos &&
                !errorRut &&
                !errorCorreo &&
                !errorAporte &&
                !errorFecha &&
                !errorCondiciones &&
                !errorMetodoPago
    }

    fun validarYGuardarSolicitud() {
        if (guardando) return

        if (!validarSolicitud()) {
            mensaje = ""
            return
        }

        viewModelScope.launch {
            guardando = true
            mensaje = ""

            try {
                val solicitud = Solicitud(
                    nombre = nombre.trim(),
                    apellidos = apellidos.trim(),
                    rut = rut.replace(".", "").trim(),
                    correo = correo.trim(),
                    aporte = aporte.toInt(),
                    fecha = fecha,
                    recibirNovedades = recibirNovedades,
                    anonima = anonima,
                    causa = causaSeleccionada,
                    aporteMensual = aporteMensual,
                    metodoPago = metodoPago
                )

                solicitudDao.insertar(solicitud)
                solicitudes = solicitudDao.obtenerTodas()
                mensaje = "¡Solicitud guardada correctamente!"

                NotificacionesLocales.enviar(
                    context = getApplication(),
                    titulo = "¡Gracias por colaborar! 💚",
                    mensaje = "Tu solicitud quedó guardada en este dispositivo. " +
                            "No se ha procesado ningún pago."
                )
            } catch (e: Exception) {
                mensaje = "No se pudo guardar la solicitud."
            } finally {
                guardando = false
            }
        }
    }
}
