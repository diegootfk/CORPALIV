package com.example.corpaliv.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.corpaliv.ui.viewmodel.ColaborarViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

private val VerdeColaborar = Color(0xFF176B50)
private val FondoColaborar = Color(0xFFF5F7F4)

private fun formatearMontoCLP(valor: String): String {
    val digitos = valor.filter { it.isDigit() }
    if (digitos.isEmpty()) return ""
    val numero = digitos.toLongOrNull() ?: return digitos
    val simbolos = DecimalFormatSymbols(Locale("es", "CL")).apply {
        groupingSeparator = '.'
    }
    return DecimalFormat("#,###", simbolos).format(numero)
}

@Composable
fun ColaborarScreen(
    modifier: Modifier = Modifier,
    modelo: ColaborarViewModel = viewModel()
) {
    var mostrarSelectorFecha by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val solicitudPermisoNotificaciones =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            // La solicitud se guarda incluso si se rechaza el permiso.
            modelo.validarYGuardarSolicitud()
        }

    val colores = MaterialTheme.colorScheme
    val esOscuro = colores.background == Color(0xFF121212)
    val esAltoContraste = colores.background == Color.White &&
            colores.onBackground == Color.Black

    val fondo = when {
        esOscuro -> Color(0xFF121212)
        esAltoContraste -> Color.White
        else -> FondoColaborar
    }
    val verde = when {
        esOscuro -> Color(0xFF278A68)
        esAltoContraste -> Color(0xFF003D2B)
        else -> VerdeColaborar
    }
    val verdeSuave = when {
        esOscuro -> Color(0xFF263A33)
        esAltoContraste -> Color(0xFFE8F3EE)
        else -> Color(0xFFE4F1E9)
    }
    val colorTarjeta = colores.surface
    val textoPrincipal = colores.onSurface
    val textoSecundario = when {
        esOscuro -> Color(0xFFD0D0D0)
        esAltoContraste -> Color.Black
        else -> colores.onSurfaceVariant
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(verde, RoundedCornerShape(20.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quiero colaborar",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Juntos construimos más oportunidades.",
                fontSize = 15.sp,
                color = Color.White
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colorTarjeta),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "¿Por qué elegir CORPALIV?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "💚 Inclusión y oportunidades",
                    fontWeight = FontWeight.SemiBold,
                    color = textoPrincipal
                )
                Text(
                    text = "Tu colaboración puede apoyar iniciativas educativas y sociolaborales para personas con discapacidad.",
                    color = textoSecundario
                )
                Text(
                    text = "🤝 Apoyo a las familias",
                    fontWeight = FontWeight.SemiBold,
                    color = textoPrincipal
                )
                Text(
                    text = "Contribuye a una comunidad más inclusiva, con mayores oportunidades de participación.",
                    color = textoSecundario
                )
                Text(
                    text = "🌱 Compromiso con la inclusión",
                    fontWeight = FontWeight.SemiBold,
                    color = textoPrincipal
                )
                Text(
                    text = "Cada gesto solidario puede ayudar a impulsar un entorno más accesible y participativo.",
                    color = textoSecundario
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colorTarjeta),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Registrar aporte solidario",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "Completa tus datos para registrar tu interés. Esta aplicación no procesa pagos.",
                    color = textoSecundario
                )

                OutlinedTextField(
                    value = modelo.nombre,
                    onValueChange = {
                        modelo.nombre = it
                        modelo.mensaje = ""
                    },
                    label = { Text("Nombre o empresa") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = modelo.errorNombre,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )
                if (modelo.errorNombre) {
                    Text("Ingresa tu nombre o empresa.", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = modelo.rut,
                    onValueChange = modelo::actualizarRut,
                    label = { Text("RUT") },
                    placeholder = { Text("12.345.678-5") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = modelo.errorRut,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )
                if (modelo.errorRut) {
                    Text("Ingresa un RUT chileno válido.", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = modelo.correo,
                    onValueChange = {
                        modelo.correo = it
                        modelo.mensaje = ""
                    },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = modelo.errorCorreo,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )
                if (modelo.errorCorreo) {
                    Text("Ingresa un correo válido.", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = formatearMontoCLP(modelo.aporte),
                    onValueChange = { valor ->
                        modelo.aporte = valor.filter { it.isDigit() }
                        modelo.mensaje = ""
                    },
                    label = { Text("Monto del aporte (CLP)") },
                    placeholder = { Text("Ej. 32.500") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = modelo.errorAporte,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )
                if (modelo.errorAporte) {
                    Text("Ingresa un aporte mayor que cero.", color = MaterialTheme.colorScheme.error)
                }

                Text(
                    text = "Fecha de colaboración",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = textoPrincipal
                )
                OutlinedButton(
                    onClick = { mostrarSelectorFecha = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = verde)
                ) {
                    Text(if (modelo.fecha.isBlank()) "Seleccionar fecha" else modelo.fecha)
                }
                if (modelo.errorFecha) {
                    Text("Selecciona una fecha válida.", color = MaterialTheme.colorScheme.error)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = modelo.anonima,
                        onCheckedChange = {
                            modelo.anonima = it
                            modelo.mensaje = ""
                        }
                    )
                    Text(
                        text = "Quiero que mi aporte sea anónimo",
                        modifier = Modifier.weight(1f),
                        color = textoPrincipal
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = modelo.aceptaCondiciones,
                        onCheckedChange = {
                            modelo.aceptaCondiciones = it
                            modelo.mensaje = ""
                        }
                    )
                    Text(
                        text = "Acepto las condiciones de colaboración",
                        modifier = Modifier.weight(1f),
                        color = textoPrincipal
                    )
                }
                if (modelo.errorCondiciones) {
                    Text("Debes aceptar las condiciones.", color = MaterialTheme.colorScheme.error)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Deseo recibir novedades",
                        modifier = Modifier.weight(1f),
                        color = textoPrincipal
                    )
                    Switch(
                        checked = modelo.recibirNovedades,
                        onCheckedChange = {
                            modelo.recibirNovedades = it
                            modelo.mensaje = ""
                        }
                    )
                }

                Button(
                    onClick = {
                        if (modelo.validarSolicitud()) {
                            if (
                                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.POST_NOTIFICATIONS
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                solicitudPermisoNotificaciones.launch(
                                    Manifest.permission.POST_NOTIFICATIONS
                                )
                            } else {
                                modelo.validarYGuardarSolicitud()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    enabled = !modelo.guardando,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = verde,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = if (modelo.guardando) "Guardando..." else "Registrar mi interés"
                    )
                }

                if (modelo.mensaje.isNotBlank()) {
                    val esExito = modelo.mensaje == "¡Solicitud guardada correctamente!"
                    Text(
                        text = modelo.mensaje,
                        color = if (esExito) verde else MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        Text(
            text = "El registro se almacena localmente en el dispositivo. No se realiza ningún cobro ni se envía una donación a CORPALIV.",
            style = MaterialTheme.typography.bodySmall,
            color = textoSecundario
        )
    }

    if (mostrarSelectorFecha) {
        val zonaHoraria = remember { TimeZone.getDefault() }
        val inicioDia = remember {
            Calendar.getInstance(zonaHoraria).apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
        }
        val estadoFecha = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val calendario = Calendar.getInstance(zonaHoraria)
                    calendario.timeInMillis = utcTimeMillis
                    calendario.set(Calendar.HOUR_OF_DAY, 0)
                    calendario.set(Calendar.MINUTE, 0)
                    calendario.set(Calendar.SECOND, 0)
                    calendario.set(Calendar.MILLISECOND, 0)
                    return calendario.timeInMillis >= inicioDia
                }
            }
        )

        DatePickerDialog(
            onDismissRequest = { mostrarSelectorFecha = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        estadoFecha.selectedDateMillis?.let { milisegundos ->
                            val calendario = Calendar.getInstance(zonaHoraria)
                            calendario.timeInMillis = milisegundos
                            modelo.fecha = SimpleDateFormat(
                                "dd/MM/yyyy",
                                Locale.getDefault()
                            ).apply {
                                timeZone = zonaHoraria
                            }.format(calendario.time)
                            modelo.errorFecha = false
                            modelo.mensaje = ""
                        }
                        mostrarSelectorFecha = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarSelectorFecha = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = estadoFecha)
        }
    }
}
