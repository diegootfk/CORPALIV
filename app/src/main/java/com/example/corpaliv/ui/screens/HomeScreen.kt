package com.example.corpaliv.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalConfiguration
import com.example.corpaliv.R

private val VerdeCorpaliv = Color(0xFF176B50)
private val FondoInicio = Color(0xFFF5F7F4)
private val VerdeSuave = Color(0xFFE4F1E9)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onProgramasClick: () -> Unit = {},
    onColaborarClick: () -> Unit = {},
    onCompartirClick: () -> Unit = {}
) {
    val configuracion = LocalConfiguration.current
    val horizontal = configuracion.screenWidthDp > configuracion.screenHeightDp

    val colores = MaterialTheme.colorScheme
    val esOscuro = colores.background == Color(0xFF121212)
    val esAltoContraste = colores.background == Color.White &&
            colores.onBackground == Color.Black

    val fondo = when {
        esOscuro -> Color(0xFF121212)
        esAltoContraste -> Color.White
        else -> FondoInicio
    }
    val verde = when {
        esOscuro -> Color(0xFF278A68)
        esAltoContraste -> Color(0xFF003D2B)
        else -> VerdeCorpaliv
    }
    val verdeSuave = when {
        esOscuro -> Color(0xFF263A33)
        esAltoContraste -> Color(0xFFE8F3EE)
        else -> VerdeSuave
    }
    val textoPrincipal = colores.onSurface
    val textoSecundario = when {
        esOscuro -> Color(0xFFD0D0D0)
        esAltoContraste -> Color.Black
        else -> Color(0xFF59645E)
    }
    val colorTarjeta = colores.surface
    val altoLogo = if (horizontal) 55.dp else 82.dp
    val altoImagen = if (horizontal) 135.dp else 210.dp
    val espacio = if (horizontal) 10.dp else 18.dp
    val paddingContenido = if (horizontal) 14.dp else 18.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(vertical = espacio),
        verticalArrangement = Arrangement.spacedBy(espacio),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingContenido),
            verticalArrangement = Arrangement.spacedBy(espacio),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_corpaliv),
                contentDescription = "Logo oficial de CORPALIV",
                modifier = Modifier.fillMaxWidth().height(altoLogo),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Inclusión que transforma vidas",
                fontSize = if (horizontal) 14.sp else 16.sp,
                color = textoSecundario,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.corpaliv_inclusion_real),
                contentDescription = "Estudiante de la Escuela Especial Jan Van Dijk participando en una actividad junto a una profesional",
                modifier = Modifier.fillMaxWidth().height(altoImagen).clip(RoundedCornerShape(22.dp)),
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = verde),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(if (horizontal) 16.dp else 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Bienvenido a CORPALIV",
                        fontSize = if (horizontal) 20.sp else 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Somos una corporación comprometida con " +
                                "la inclusión y el desarrollo de personas con " +
                                "discapacidad múltiple.",
                        fontSize = 14.sp,
                        color = Color.White,
                        lineHeight = 21.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "¿Cómo puedes participar?",
                    fontSize = if (horizontal) 20.sp else 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "Conoce nuestra labor y descubre cómo contribuir " +
                            "a una sociedad con más oportunidades.",
                    fontSize = 14.sp,
                    color = textoSecundario,
                    lineHeight = 21.sp
                )
            }

            TarjetaInicio(
                emoji = "💚",
                titulo = "Aportes solidarios",
                descripcion = "Registra tu interés en realizar un aporte y selecciona la fecha que prefieras.",
                textoBoton = "Quiero colaborar",
                onClick = onColaborarClick,
                horizontal = horizontal,
                colorTarjeta = colorTarjeta,
                colorTexto = textoPrincipal,
                colorTextoSecundario = textoSecundario,
                colorVerde = verde
            )

            TarjetaInicio(
                emoji = "📚",
                titulo = "Nuestros programas",
                descripcion = "Conoce la Escuela Especial Jan Van Dijk y la Red de Inclusión Sociolaboral Alba Lab.",
                textoBoton = "Descubrir programas",
                onClick = onProgramasClick,
                horizontal = horizontal,
                colorTarjeta = colorTarjeta,
                colorTexto = textoPrincipal,
                colorTextoSecundario = textoSecundario,
                colorVerde = verde
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = verdeSuave)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Juntos construimos más oportunidades 🌱",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (esOscuro) Color(0xFFB7F0D8) else verde,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Cada persona tiene capacidades, talentos y sueños que merecen ser reconocidos.",
                        fontSize = 14.sp,
                        color = if (esOscuro) Color(0xFFE0F2E9) else verde,
                        textAlign = TextAlign.Center,
                        lineHeight = 21.sp
                    )
                }
            }

            Button(
                onClick = onCompartirClick,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = verde,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Compartir CORPALIV",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }

        // Imagen panorámica al final de Inicio, a todo el ancho disponible.
        Image(
            painter = painterResource(id = R.drawable.abajo),
            contentDescription = "Transformamos vidas: mensaje de inclusión de CORPALIV",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2048f / 460f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun TarjetaInicio(
    emoji: String,
    titulo: String,
    descripcion: String,
    textoBoton: String,
    onClick: () -> Unit,
    horizontal: Boolean,
    colorTarjeta: Color,
    colorTexto: Color,
    colorTextoSecundario: Color,
    colorVerde: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorTarjeta),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(if (horizontal) 14.dp else 18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "$emoji  $titulo",
                fontSize = if (horizontal) 17.sp else 19.sp,
                fontWeight = FontWeight.Bold,
                color = colorVerde
            )
            Text(
                text = descripcion,
                fontSize = 14.sp,
                color = colorTextoSecundario,
                lineHeight = 21.sp
            )
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth().height(46.dp),
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorVerde,
                    contentColor = Color.White
                )
            ) {
                Text(text = textoBoton, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
