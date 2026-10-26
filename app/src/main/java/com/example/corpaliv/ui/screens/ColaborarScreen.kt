package com.example.corpaliv.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.corpaliv.R
import com.example.corpaliv.ui.viewmodel.ColaborarViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.isSystemInDarkTheme

private val VerdeColaborar = Color(0xFF176B50)
private val FondoColaborar = Color(0xFFF5F7F4)
private val RosaDonar = Color(0xFFC4317B)

private data class CausaColaborar(
    val titulo: String,
    val descripcion: String,
    val emoji: String,
    val imagen: Int
)

private val causasColaborar = listOf(
    CausaColaborar(
        "Educación inclusiva",
        "Apoya oportunidades de aprendizaje y educación.",
        "📚",
        R.drawable.educ_inclusiva
    ),
    CausaColaborar(
        "Inclusión laboral",
        "Contribuye a iniciativas de formación sociolaboral.",
        "💼",
        R.drawable.inclusion_laboral
    ),
    CausaColaborar(
        "Talleres y desarrollo de habilidades",
        "Apoya actividades creativas y de desarrollo personal.",
        "🎨",
        R.drawable.taller
    ),
    CausaColaborar(
        "Bienestar y autonomía",
        "Promueve la autonomía y participación comunitaria.",
        "🌱",
        R.drawable.bienestar
    ),
    CausaColaborar(
        "Donación general",
        "Un aporte de libre elección para apoyar la labor de CORPALIV.",
        "💚",
        R.drawable.donaciongeneral
    )
)

private val metodosPago = listOf(
    "Tarjeta de crédito o débito",
    "Tarjeta de débito o prepago",
    "Transferencia bancaria"
)

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
    val colores = MaterialTheme.colorScheme

    val esOscuro = isSystemInDarkTheme()
    val fondo = if (esOscuro) Color(0xFF121212) else FondoColaborar
    val verde = if (esOscuro) Color(0xFF278A68) else VerdeColaborar
    val tarjeta = colores.surface
    val texto = colores.onSurface
    val secundario = colores.onSurfaceVariant

    val solicitudPermisoNotificaciones =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            modelo.validarYGuardarSolicitud()
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
                text = "Colabora con CORPALIV",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "¡Eres genial! Gracias por tu compromiso 💚",
                color = Color.White
            )
        }

        if (modelo.causaSeleccionada.isBlank()) {
            Text(
                "Elige una causa",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = verde
            )
            Text(
                "Selecciona la causa con la que te gustaría colaborar.",
                color = secundario
            )

            causasColaborar.forEach { causa ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = tarjeta
                    ),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = causa.imagen),
                            contentDescription = causa.titulo,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(causa.emoji, fontSize = 32.sp)
                            Text(
                                causa.titulo,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = verde
                            )
                            Text(causa.descripcion, color = secundario)
                            Button(
                                onClick = { modelo.seleccionarCausa(causa.titulo) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = verde
                                )
                            ) {
                                Text("Donar a esta causa")
                            }
                        }
                    }
                }
            }
        } else {
            TextButton(onClick = { modelo.volverACausas() }) {
                Text("← Cambiar causa", color = verde)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = tarjeta),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(Modifier.padding(18.dp)) {
                    val imagenSeleccionada = causasColaborar
                        .firstOrNull { it.titulo == modelo.causaSeleccionada }
                        ?.imagen

                    if (imagenSeleccionada != null) {
                        Image(
                            painter = painterResource(id = imagenSeleccionada),
                            contentDescription = modelo.causaSeleccionada,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.height(12.dp))
                    }

                    Text("Tu causa", color = secundario)
                    Text(
                        modelo.causaSeleccionada,
                        color = verde,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    if (modelo.causaSeleccionada == "Educación inclusiva") {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "La educación inclusiva es fundamental para que " +
                                    "niños, niñas y jóvenes con discapacidad múltiple y " +
                                    "sordoceguera puedan desarrollar al máximo sus capacidades.\n\n" +
                                    "En CORPALIV, a través de la Escuela Especial Jan Van Dijk, " +
                                    "se entrega una educación especializada que considera " +
                                    "las necesidades, habilidades y formas de comunicación " +
                                    "de cada estudiante, promoviendo su autonomía, aprendizaje " +
                                    "y participación en la sociedad.\n\n" +
                                    "Tu aporte contribuye a fortalecer las oportunidades de " +
                                    "aprendizaje, el desarrollo de habilidades y la construcción " +
                                    "de un futuro con mayor inclusión y bienestar.",
                            color = secundario,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (modelo.causaSeleccionada == "Inclusión laboral") {
                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "La inclusión laboral permite que las personas con " +
                                    "discapacidad desarrollen sus capacidades, fortalezcan " +
                                    "su autonomía y participen activamente en el mundo del trabajo.\n\n" +
                                    "En CORPALIV, a través de Alba Lab, se promueve la formación " +
                                    "sociolaboral mediante experiencias que potencian las habilidades " +
                                    "sociales, emocionales y laborales. Además, se impulsa la " +
                                    "elaboración de productos y el desarrollo de competencias que " +
                                    "favorecen la participación y la inclusión en entornos laborales.\n\n" +
                                    "Tu aporte contribuye a abrir oportunidades, fortalecer la " +
                                    "independencia y construir una sociedad donde todas las personas " +
                                    "puedan desarrollar sus talentos y sentirse parte de ella.",
                            color = secundario,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (modelo.causaSeleccionada == "Talleres y desarrollo de habilidades") {
                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "Los talleres son espacios de aprendizaje, creatividad y " +
                                    "desarrollo personal que permiten a niños, niñas y jóvenes " +
                                    "fortalecer sus habilidades y descubrir nuevas formas de expresarse.\n\n" +
                                    "En CORPALIV se promueven experiencias que potencian las " +
                                    "capacidades de cada persona mediante actividades adaptadas " +
                                    "a sus necesidades, favoreciendo la participación, la autonomía " +
                                    "y el trabajo colaborativo.\n\n" +
                                    "Tu aporte ayuda a impulsar oportunidades para aprender, crear " +
                                    "y desarrollar nuevas habilidades, construyendo un entorno donde " +
                                    "cada persona pueda crecer y alcanzar su máximo potencial.",
                            color = secundario,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (modelo.causaSeleccionada == "Bienestar y autonomía") {
                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "El bienestar y la autonomía son fundamentales para que " +
                                    "niños, niñas y jóvenes con discapacidad puedan desarrollar " +
                                    "sus capacidades, fortalecer su independencia y participar " +
                                    "activamente en su entorno.\n\n" +
                                    "En CORPALIV se promueve el desarrollo integral de cada persona, " +
                                    "respetando sus necesidades y potenciando sus habilidades para " +
                                    "favorecer una mayor autonomía en la vida cotidiana, la " +
                                    "comunicación y la participación social.\n\n" +
                                    "Tu aporte ayuda a impulsar oportunidades para que cada persona " +
                                    "pueda desarrollar sus habilidades, tomar parte en su comunidad " +
                                    "y avanzar hacia una vida con mayor independencia, bienestar " +
                                    "e inclusión.",
                            color = secundario,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (modelo.causaSeleccionada == "Donación general") {
                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "Cada aporte es una oportunidad para seguir construyendo " +
                                    "una sociedad más inclusiva, donde niños, niñas y jóvenes " +
                                    "con discapacidad múltiple y sordoceguera puedan desarrollar " +
                                    "sus capacidades y participar activamente en la comunidad.\n\n" +
                                    "En CORPALIV trabajamos para promover la educación especializada, " +
                                    "el desarrollo integral, la autonomía y la inclusión social " +
                                    "y laboral, acompañando a cada persona de acuerdo con sus " +
                                    "necesidades y habilidades.\n\n" +
                                    "Tu donación, sin importar su monto, representa un gesto de " +
                                    "solidaridad que contribuye a fortalecer esta labor y a generar " +
                                    "nuevas oportunidades para las personas y sus familias. Gracias " +
                                    "por ser parte de una comunidad que cree en la inclusión.",
                            color = secundario,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = tarjeta),
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        "Tu aporte hace la diferencia",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde
                    )
                    Text(
                        "Ingresa el monto que quieres donar",
                        fontWeight = FontWeight.SemiBold,
                        color = texto
                    )
                    OutlinedTextField(
                        value = modelo.aporte,
                        onValueChange = { valor ->
                            modelo.aporte = valor.filter { it.isDigit() }
                            modelo.mensaje = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ej. 10.000") },
                        suffix = { Text("CLP") },
                        singleLine = true,
                        isError = modelo.errorAporte,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = remember { ClpVisualTransformation() }
                    )
                    if (modelo.errorAporte) {
                        TextoError("Ingresa un monto mayor que cero.")
                    }

                    Text("Tus datos", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = verde)

                    OutlinedTextField(
                        value = modelo.nombre,
                        onValueChange = {
                            modelo.nombre = it
                            modelo.mensaje = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Nombre") },
                        singleLine = true,
                        isError = modelo.errorNombre
                    )
                    if (modelo.errorNombre) {
                        TextoError("Ingresa tu nombre.")
                    }

                    OutlinedTextField(
                        value = modelo.apellidos,
                        onValueChange = {
                            modelo.apellidos = it
                            modelo.mensaje = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Apellidos") },
                        singleLine = true,
                        isError = modelo.errorApellidos
                    )
                    if (modelo.errorApellidos) {
                        TextoError("Ingresa tus apellidos.")
                    }

                    OutlinedTextField(
                        value = modelo.rut,
                        onValueChange = modelo::actualizarRut,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("RUT") },
                        placeholder = { Text("12.345.678-5") },
                        singleLine = true,
                        isError = modelo.errorRut,
                        visualTransformation = remember { RutVisualTransformation() }
                    )
                    if (modelo.errorRut) {
                        TextoError("Ingresa un RUT chileno válido.")
                    }

                    OutlinedTextField(
                        value = modelo.correo,
                        onValueChange = {
                            modelo.correo = it
                            modelo.mensaje = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Correo electrónico") },
                        placeholder = { Text("correo@ejemplo.com") },
                        singleLine = true,
                        isError = modelo.errorCorreo
                    )
                    if (modelo.errorCorreo) {
                        TextoError("Ingresa un correo válido.")
                    }

                    Text(
                        "¿Quieres hacerte socio con un aporte mensual?",
                        fontWeight = FontWeight.SemiBold,
                        color = texto
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = modelo.aporteMensual,
                            onClick = { modelo.aporteMensual = true }
                        )
                        Text("Sí 💗", color = texto)
                        Spacer(Modifier.height(1.dp))
                        RadioButton(
                            selected = !modelo.aporteMensual,
                            onClick = { modelo.aporteMensual = false }
                        )
                        Text("No", color = texto)
                    }

                    Text(
                        "Selecciona medio de pago",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde
                    )
                    metodosPago.forEach { metodo ->
                        val estaSeleccionado = modelo.metodoPago == metodo

                        // Color de fondo adaptado para modo claro y modo oscuro
                        val fondoTarjeta = when {
                            estaSeleccionado && esOscuro -> Color(0xFF1E3F33) // Verde oscuro para dark mode
                            estaSeleccionado -> Color(0xFFE4F1E9)             // Verde claro para light mode
                            else -> tarjeta
                        }

                        // Color del texto ajustado para mantener alto contraste
                        val colorTexto = when {
                            estaSeleccionado && esOscuro -> Color.White
                            estaSeleccionado -> VerdeColaborar
                            else -> texto
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = fondoTarjeta)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = estaSeleccionado,
                                    onClick = { modelo.metodoPago = metodo }
                                )
                                Text(
                                    metodo,
                                    modifier = Modifier.weight(1f),
                                    color = colorTexto
                                )
                            }
                        }
                    }
                    if (modelo.errorMetodoPago) {
                        TextoError("Selecciona un medio de pago.")
                    }

                    Text(
                        "Fecha de colaboración",
                        fontWeight = FontWeight.SemiBold,
                        color = texto
                    )
                    OutlinedButton(
                        onClick = { mostrarSelectorFecha = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (modelo.fecha.isBlank()) {
                                "Seleccionar fecha"
                            } else {
                                modelo.fecha
                            }
                        )
                    }
                    if (modelo.errorFecha) {
                        TextoError("Selecciona una fecha.")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = modelo.recibirNovedades,
                            onCheckedChange = { modelo.recibirNovedades = it }
                        )
                        Text(
                            "Acepto recibir comunicaciones de CORPALIV.",
                            color = texto
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
                            "He leído y acepto los términos y condiciones.",
                            color = texto
                        )
                    }
                    if (modelo.errorCondiciones) {
                        TextoError("Debes aceptar las condiciones.")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = modelo.anonima,
                            onCheckedChange = { modelo.anonima = it }
                        )
                        Text("Quiero que mi aporte sea anónimo.", color = texto)
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
                            } else {
                                modelo.mensaje = ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        enabled = !modelo.guardando,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RosaDonar,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            if (modelo.guardando) "Guardando..." else "Donar",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (modelo.mensaje.isNotBlank()) {
                        Text(
                            modelo.mensaje,
                            color = if (
                                modelo.mensaje == "¡Solicitud guardada correctamente!"
                            ) verde else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
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

@Composable
private fun TextoError(texto: String) {
    Text(
        text = texto,
        color = MaterialTheme.colorScheme.error
    )
}

class RutVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text
        if (raw.isEmpty()) return TransformedText(text, OffsetMapping.Identity)

        val formatted = if (raw.length <= 1) {
            raw
        } else {
            val cuerpo = raw.dropLast(1)
            val dv = raw.last()
            val cuerpoConPuntos = cuerpo.reversed()
                .chunked(3)
                .joinToString(".")
                .reversed()
            "$cuerpoConPuntos-$dv"
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > raw.length) return formatted.length

                var transformedOffset = 0
                var rawCount = 0
                for (char in formatted) {
                    if (rawCount == offset) break
                    if (char.isLetterOrDigit()) rawCount++
                    transformedOffset++
                }
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > formatted.length) return raw.length

                var rawOffset = 0
                for (i in 0 until offset.coerceAtMost(formatted.length)) {
                    if (formatted[i].isLetterOrDigit()) rawOffset++
                }
                return rawOffset
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

class ClpVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text.filter { it.isDigit() }
        if (raw.isEmpty()) return TransformedText(text, OffsetMapping.Identity)

        val numero = raw.toLongOrNull() ?: return TransformedText(text, OffsetMapping.Identity)
        val simbolos = DecimalFormatSymbols(Locale("es", "CL")).apply {
            groupingSeparator = '.'
        }
        val formatted = DecimalFormat("#,###", simbolos).format(numero)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > raw.length) return formatted.length

                var transformedOffset = 0
                var digitCount = 0
                for (char in formatted) {
                    if (digitCount == offset) break
                    if (char.isDigit()) digitCount++
                    transformedOffset++
                }
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > formatted.length) return raw.length

                var rawOffset = 0
                for (i in 0 until offset.coerceAtMost(formatted.length)) {
                    if (formatted[i].isDigit()) rawOffset++
                }
                return rawOffset
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}
