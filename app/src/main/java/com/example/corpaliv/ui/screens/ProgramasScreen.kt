package com.example.corpaliv.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.corpaliv.R
import java.util.Locale

private val VerdeCorpaliv = Color(0xFF176B50)
private val FondoProgramas = Color(0xFFF5F7F4)
private val VerdeSuave = Color(0xFFE4F1E9)

@Composable
fun ProgramasScreen(modifier: Modifier = Modifier) {
    var mostrarEscuela by remember { mutableStateOf(false) }
    var mostrarAlbaLab by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }

    val colores = MaterialTheme.colorScheme
    val fondo = when {
        colores.background == Color.White && colores.onBackground == Color.Black -> Color.White
        colores.background == Color(0xFF121212) -> Color(0xFF121212)
        else -> FondoProgramas
    }
    val colorTextoSecundario = colores.onSurfaceVariant
    val verde = if (colores.background == Color(0xFF121212)) Color(0xFF278A68)
    else if (colores.background == Color.White && colores.onBackground == Color.Black) Color(0xFF003D2B)
    else VerdeCorpaliv
    val fondoSuave = if (colores.background == Color(0xFF121212)) Color(0xFF263A33)
    else if (colores.background == Color.White && colores.onBackground == Color.Black) Color(0xFFE8F3EE)
    else VerdeSuave

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
                    text = "Nuestros programas",
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 33.sp
                )
                Text(
                    text = "Acompañamos el desarrollo de personas con discapacidad múltiple, promoviendo oportunidades de aprendizaje, participación e inclusión.",
                    color = Color.White,
                    fontSize = 15.sp,
                    lineHeight = 23.sp
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Dos espacios, un propósito",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = verde
            )
            Text(
                text = "CORPALIV desarrolla su labor mediante el trabajo educativo y la promoción de oportunidades sociolaborales.",
                fontSize = 15.sp,
                color = colorTextoSecundario,
                lineHeight = 22.sp
            )
        }

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            label = { Text("Buscar programas") },
            placeholder = { Text("Ej. Alba, educativa, sociolaboral...") },
            leadingIcon = { Text("🔎") },
            trailingIcon = {
                if (busqueda.isNotBlank()) {
                    androidx.compose.material3.TextButton(onClick = { busqueda = "" }) {
                        Text("Limpiar")
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = verde,
                focusedLabelColor = verde,
                cursorColor = verde
            )
        )

        val consulta = busqueda.trim().lowercase(Locale.getDefault())
        val mostrarEscuelaTarjeta = consulta.isBlank() ||
                listOf(
                    "escuela especial jan van dijk",
                    "educación y desarrollo",
                    "educativa",
                    "aprendizaje",
                    "desarrollo",
                    "discapacidad múltiple",
                    "jan van dijk"
                ).any { it.lowercase(Locale.getDefault()).contains(consulta) }

        val mostrarAlbaLabTarjeta = consulta.isBlank() ||
                listOf(
                    "red de inclusión sociolaboral alba lab",
                    "alba lab",
                    "oportunidades para jóvenes y adultos",
                    "sociolaboral",
                    "inclusión",
                    "habilidades",
                    "empresas"
                ).any { it.lowercase(Locale.getDefault()).contains(consulta) }

        if (consulta.isNotBlank() && !mostrarEscuelaTarjeta && !mostrarAlbaLabTarjeta) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colores.surface)
            ) {
                Text(
                    text = "No encontramos programas para \"$busqueda\". Prueba con otra palabra.",
                    modifier = Modifier.padding(18.dp),
                    color = colores.onSurface,
                    fontSize = 15.sp
                )
            }
        }

        if (mostrarEscuelaTarjeta) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = colores.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.escuela_jan_van_dijk),
                        contentDescription = "Comunidad de la Escuela Especial Jan Van Dijk",
                        modifier = Modifier.fillMaxWidth().height(190.dp).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier.background(fondoSuave, RoundedCornerShape(14.dp)).padding(13.dp)
                    ) {
                        Text(text = "📚", fontSize = 28.sp)
                    }
                    Text(
                        text = "Escuela Especial Jan Van Dijk",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde,
                        lineHeight = 27.sp
                    )
                    Text(
                        text = "Educación y desarrollo",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorTextoSecundario
                    )
                    Text(
                        text = "La escuela trabaja en la educación de niños, niñas y jóvenes con discapacidad múltiple, favoreciendo el aprendizaje, el desarrollo y la participación de acuerdo con sus necesidades y capacidades.",
                        fontSize = 15.sp,
                        color = colores.onSurface,
                        lineHeight = 23.sp
                    )
                    AnimatedVisibility(visible = mostrarEscuela, enter = fadeIn(), exit = fadeOut()) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Su labor busca:", fontWeight = FontWeight.Bold, color = verde)
                            TextoPunto("Favorecer el aprendizaje y el desarrollo.", verde, colores.onSurface)
                            TextoPunto("Respetar las necesidades y diferencias de cada persona.", verde, colores.onSurface)
                            TextoPunto("Promover la participación en un entorno educativo inclusivo.", verde, colores.onSurface)
                        }
                    }
                    Button(
                        onClick = { mostrarEscuela = !mostrarEscuela },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = verde,
                            contentColor = Color.White
                        )
                    ) {
                        Text(if (mostrarEscuela) "Ver menos" else "Conocer más")
                    }
                }
            }
        }

        if (mostrarAlbaLabTarjeta) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = colores.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.albalab_cumple_un_ano),
                        contentDescription = "Integrantes de la Red de Inclusión Sociolaboral Alba Lab",
                        modifier = Modifier.fillMaxWidth().height(190.dp).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier.background(fondoSuave, RoundedCornerShape(14.dp)).padding(13.dp)
                    ) {
                        Text(text = "🤝", fontSize = 28.sp)
                    }
                    Text(
                        text = "Red de Inclusión Sociolaboral Alba Lab",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde,
                        lineHeight = 27.sp
                    )
                    Text(
                        text = "Oportunidades para jóvenes y adultos",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorTextoSecundario
                    )
                    Text(
                        text = "Alba Lab es una red sociolaboral orientada a promover la inclusión de jóvenes y adultos con discapacidad múltiple, abriendo oportunidades para desarrollar habilidades, aprender y participar en el ámbito laboral.",
                        fontSize = 15.sp,
                        color = colores.onSurface,
                        lineHeight = 23.sp
                    )
                    AnimatedVisibility(visible = mostrarAlbaLab, enter = fadeIn(), exit = fadeOut()) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Su enfoque busca:", fontWeight = FontWeight.Bold, color = verde)
                            TextoPunto("Promover el desarrollo de habilidades.", verde, colores.onSurface)
                            TextoPunto("Favorecer oportunidades de participación sociolaboral.", verde, colores.onSurface)
                            TextoPunto("Conectar la inclusión con la colaboración de la comunidad y las empresas.", verde, colores.onSurface)
                        }
                    }
                    Button(
                        onClick = { mostrarAlbaLab = !mostrarAlbaLab },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = verde,
                            contentColor = Color.White
                        )
                    ) {
                        Text(if (mostrarAlbaLab) "Ver menos" else "Conocer más")
                    }
                }
            }
        }

        Text(
            text = "Nuestros valores",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = verde
        )
        TarjetaValor("🤝", "Compromiso", "Trabajar por el desarrollo y la inclusión.", colores.surface, verde, colores.onSurface, fondoSuave)
        TarjetaValor("💚", "Respeto y empatía", "Reconocer las necesidades, capacidades y diferencias de cada persona.", colores.surface, verde, colores.onSurface, fondoSuave)
        TarjetaValor("🌱", "Solidaridad", "Promover la colaboración para construir una sociedad con menos barreras.", colores.surface, verde, colores.onSurface, fondoSuave)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = fondoSuave)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Cada persona merece oportunidades",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Conocer, participar y colaborar son pasos importantes hacia una sociedad más inclusiva.",
                    fontSize = 15.sp,
                    color = verde,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun TextoPunto(texto: String, colorVerde: Color, colorTexto: Color) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "•", color = colorVerde, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = texto, modifier = Modifier.weight(1f), fontSize = 14.sp, color = colorTexto, lineHeight = 21.sp)
    }
}

@Composable
private fun TarjetaValor(
    simbolo: String,
    titulo: String,
    descripcion: String,
    colorTarjeta: Color,
    colorTitulo: Color,
    colorTexto: Color,
    colorIcono: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = colorTarjeta),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier.background(colorIcono, RoundedCornerShape(12.dp)).padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = simbolo, fontSize = 24.sp)
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(text = titulo, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colorTitulo)
                Text(text = descripcion, fontSize = 14.sp, color = colorTexto, lineHeight = 20.sp)
            }
        }
    }
}
