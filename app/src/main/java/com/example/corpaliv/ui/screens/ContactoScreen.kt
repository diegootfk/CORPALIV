package com.example.corpaliv.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.corpaliv.ui.viewmodel.ContactoViewModel

private val VerdeContacto = Color(0xFF176B50)
private val FondoContacto = Color(0xFFF5F7F4)
private val VerdeSuaveContacto = Color(0xFFE4F1E9)

@Composable
fun ContactoScreen(
    modifier: Modifier = Modifier,
    modelo: ContactoViewModel = viewModel()
) {
    val motivos = listOf(
        "Servicios para empresas",
        "Alianza institucional",
        "Otra consulta"
    )

    var fotoCapturada by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val lanzadorCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) fotoCapturada = bitmap
    }

    val lanzadorGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.openInputStream(uri)?.use { input ->
                    fotoCapturada = BitmapFactory.decodeStream(input)
                }
            } catch (_: Exception) {
                fotoCapturada = null
            }
        }
    }

    val lanzadorPermisoNotificaciones =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            // La consulta se guarda incluso si el permiso es rechazado.
            modelo.validarYGuardar()
        }

    val colores = MaterialTheme.colorScheme
    val esOscuro = colores.background == Color(0xFF121212)
    val esAltoContraste = colores.background == Color.White &&
            colores.onBackground == Color.Black

    val fondo = when {
        esOscuro -> Color(0xFF121212)
        esAltoContraste -> Color.White
        else -> FondoContacto
    }
    val verde = when {
        esOscuro -> Color(0xFF278A68)
        esAltoContraste -> Color(0xFF003D2B)
        else -> VerdeContacto
    }
    val verdeSuave = when {
        esOscuro -> Color(0xFF263A33)
        esAltoContraste -> Color(0xFFE8F3EE)
        else -> VerdeSuaveContacto
    }
    val colorTarjeta = colores.surface
    val textoPrincipal = colores.onSurface
    val textoSecundario = when {
        esOscuro -> Color(0xFFD0D0D0)
        esAltoContraste -> Color.Black
        else -> Color.DarkGray
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = verde)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "CORPALIV",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Conversemos",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Las grandes oportunidades nacen de la colaboración. Cuéntanos tu idea y exploremos nuevas formas de promover la inclusión.",
                    color = Color.White,
                    fontSize = 15.sp,
                    lineHeight = 23.sp
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Estamos para escucharte",
                style = MaterialTheme.typography.titleLarge,
                color = verde,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Si representas a una empresa, institución u organización, puedes registrar tu consulta o propuesta de colaboración.",
                style = MaterialTheme.typography.bodyMedium,
                color = textoSecundario,
                lineHeight = 22.sp
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = colorTarjeta),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Datos de contacto",
                    style = MaterialTheme.typography.titleLarge,
                    color = verde,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Completa los siguientes campos para registrar tu interés.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoSecundario
                )

                OutlinedTextField(
                    value = modelo.nombre,
                    onValueChange = modelo::actualizarNombre,
                    label = { Text("Nombre del contacto") },
                    isError = modelo.errorNombre,
                    supportingText = {
                        if (modelo.errorNombre) Text("Ingresa el nombre del contacto.")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )

                OutlinedTextField(
                    value = modelo.correo,
                    onValueChange = modelo::actualizarCorreo,
                    label = { Text("Correo electrónico") },
                    isError = modelo.errorCorreo,
                    supportingText = {
                        if (modelo.errorCorreo) Text("Ingresa un correo válido.")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )

                OutlinedTextField(
                    value = modelo.organizacion,
                    onValueChange = modelo::actualizarOrganizacion,
                    label = { Text("Empresa u organización") },
                    isError = modelo.errorOrganizacion,
                    supportingText = {
                        if (modelo.errorOrganizacion) Text("Ingresa el nombre de la organización.")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "¿En qué podemos ayudarte?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "Selecciona el motivo de tu consulta.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoSecundario
                )

                motivos.forEach { opcion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = if (modelo.motivo == opcion) verdeSuave else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = modelo.motivo == opcion,
                            onClick = { modelo.actualizarMotivo(opcion) }
                        )
                        Text(
                            text = opcion,
                            modifier = Modifier.padding(start = 4.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = textoPrincipal
                        )
                    }
                }

                OutlinedTextField(
                    value = modelo.mensaje,
                    onValueChange = modelo::actualizarMensaje,
                    label = { Text("Tu propuesta o consulta") },
                    placeholder = { Text("Cuéntanos cómo te gustaría colaborar...") },
                    isError = modelo.errorMensaje,
                    supportingText = {
                        if (modelo.errorMensaje) Text("Este campo es obligatorio.")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4,
                    maxLines = 6,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = verde,
                        focusedLabelColor = verde,
                        cursorColor = verde
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Fotografía adjunta (opcional)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "Puedes tomar una fotografía o seleccionar una imagen de tu galería para acompañar visualmente tu consulta. La imagen se mostrará como vista previa temporal.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoSecundario,
                    lineHeight = 21.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { lanzadorCamara.launch(null) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            contentColor = verde
                        )
                    ) {
                        Text("📷 Cámara")
                    }

                    OutlinedButton(
                        onClick = { lanzadorGaleria.launch("image/*") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            contentColor = verde
                        )
                    ) {
                        Text("🖼️ Galería")
                    }
                }

                fotoCapturada?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Vista previa de la fotografía",
                        modifier = Modifier.fillMaxWidth().height(220.dp)
                    )
                    OutlinedButton(
                        onClick = { fotoCapturada = null },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            contentColor = verde
                        )
                    ) {
                        Text("Quitar fotografía")
                    }
                }

                Button(
                    onClick = {
                        if (
                            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            lanzadorPermisoNotificaciones.launch(
                                Manifest.permission.POST_NOTIFICATIONS
                            )
                        } else {
                            modelo.validarYGuardar()
                        }
                    },
                    enabled = !modelo.guardando,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = verde,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = if (modelo.guardando) "Guardando..." else "Registrar consulta",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                AnimatedVisibility(
                    visible = modelo.estado.isNotBlank(),
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    val esExito = modelo.estado.startsWith("¡")
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (esExito) verdeSuave else colores.errorContainer
                        )
                    ) {
                        Text(
                            text = modelo.estado,
                            modifier = Modifier.padding(14.dp),
                            color = if (esExito) {
                                if (esOscuro) Color(0xFFE0F2E9) else verde
                            } else {
                                colores.onErrorContainer
                            },
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
    }
}
