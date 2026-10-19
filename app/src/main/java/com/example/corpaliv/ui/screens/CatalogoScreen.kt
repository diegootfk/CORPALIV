package com.example.corpaliv.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.corpaliv.R

private val VerdeCorpaliv = Color(0xFF176B50)
private val FondoCorpaliv = Color(0xFFF5F7F4)
private val VerdeClaro = Color(0xFFE5F2E9)

@Composable
fun CatalogoScreen(
    modifier: Modifier = Modifier
) {
    var mostrarHistoria by remember { mutableStateOf(false) }

    val colores = MaterialTheme.colorScheme
    val esOscuro = colores.background == Color(0xFF121212)
    val esAltoContraste = colores.background == Color.White &&
            colores.onBackground == Color.Black

    val fondo = when {
        esOscuro -> Color(0xFF121212)
        esAltoContraste -> Color.White
        else -> FondoCorpaliv
    }
    val verde = when {
        esOscuro -> Color(0xFF278A68)
        esAltoContraste -> Color(0xFF003D2B)
        else -> VerdeCorpaliv
    }
    val verdeClaro = when {
        esOscuro -> Color(0xFF263A33)
        esAltoContraste -> Color(0xFFE8F3EE)
        else -> VerdeClaro
    }
    val textoPrincipal = colores.onSurface
    val textoSecundario = when {
        esOscuro -> Color(0xFFD0D0D0)
        esAltoContraste -> Color.Black
        else -> colores.onSurfaceVariant
    }
    val colorTarjeta = colores.surface
    val colorTextoBoton = Color.White

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
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
                    text = "El impacto de incluir",
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 33.sp
                )
                Text(
                    text = "Conoce cómo la inclusión, las oportunidades " +
                            "y la colaboración pueden contribuir a una " +
                            "sociedad más accesible.",
                    color = Color.White,
                    fontSize = 15.sp,
                    lineHeight = 23.sp
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Cada oportunidad importa",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = verde
            )
            Text(
                text = "La inclusión se construye creando oportunidades " +
                        "de participación, desarrollo y colaboración. " +
                        "Cada historia nos recuerda el valor del apoyo y " +
                        "del trabajo conjunto.",
                style = MaterialTheme.typography.bodyMedium,
                color = textoSecundario,
                lineHeight = 22.sp
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colorTarjeta),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.victoria_historia),
                    contentDescription = "Fotografía de Victoria, protagonista de una historia compartida por CORPALIV",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "💚 Una historia de esperanza",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "La historia de Victoria",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )
                Text(
                    text = "Victoria nació extremadamente prematura, " +
                            "a las 26 semanas de gestación y con solo 680 gramos. " +
                            "Desde sus primeros días, ella y su familia " +
                            "enfrentaron importantes desafíos de salud y desarrollo.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoPrincipal,
                    lineHeight = 22.sp
                )
                Text(
                    text = "Con el amor y la perseverancia de sus padres, " +
                            "Víctor y Yasna, comenzó un camino de aprendizaje " +
                            "y búsqueda de apoyo. En CORPALIV encontraron " +
                            "orientación y acompañamiento profesional para " +
                            "favorecer el desarrollo de Victoria y reconocer " +
                            "sus capacidades.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoPrincipal,
                    lineHeight = 22.sp
                )

                AnimatedVisibility(visible = mostrarHistoria) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "El acompañamiento de CORPALIV ayudó " +
                                    "a la familia a enfrentar situaciones " +
                                    "cotidianas y a encontrar herramientas " +
                                    "para apoyar el desarrollo de su hija.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textoPrincipal,
                            lineHeight = 22.sp
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(verdeClaro, RoundedCornerShape(14.dp))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "La experiencia de Victoria y su " +
                                        "familia nos recuerda que el apoyo, " +
                                        "las oportunidades y la inclusión " +
                                        "pueden hacer una diferencia importante.",
                                color = if (esOscuro) Color(0xFFE0F2E9) else verde,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }

                Button(
                    onClick = { mostrarHistoria = !mostrarHistoria },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = verde,
                        contentColor = colorTextoBoton
                    )
                ) {
                    Text(if (mostrarHistoria) "Leer menos" else "Conocer su historia")
                }
            }
        }

        Text(
            text = "¿Qué podemos promover?",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = verde
        )

        TarjetaImpacto(
            simbolo = "🤝",
            titulo = "Inclusión y participación",
            descripcion = "Promover espacios accesibles donde las personas " +
                    "puedan participar y desarrollar sus capacidades.",
            colorTarjeta = colorTarjeta,
            colorIcono = verdeClaro,
            colorTitulo = textoPrincipal,
            colorTexto = textoSecundario
        )
        TarjetaImpacto(
            simbolo = "🌱",
            titulo = "Desarrollo de habilidades",
            descripcion = "Apoyar oportunidades de aprendizaje, autonomía " +
                    "y crecimiento personal.",
            colorTarjeta = colorTarjeta,
            colorIcono = verdeClaro,
            colorTitulo = textoPrincipal,
            colorTexto = textoSecundario
        )
        TarjetaImpacto(
            simbolo = "🏢",
            titulo = "Colaboración con empresas",
            descripcion = "Impulsar alianzas e iniciativas de responsabilidad " +
                    "social que contribuyan a una sociedad más inclusiva.",
            colorTarjeta = colorTarjeta,
            colorIcono = verdeClaro,
            colorTitulo = textoPrincipal,
            colorTexto = textoSecundario
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = verdeClaro)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = "🛍️", fontSize = 30.sp)
                Text(
                    text = "Productos y servicios con impacto social",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (esOscuro) Color(0xFFB7F0D8) else verde
                )
                Text(
                    text = "Conoce la oferta disponible de CORPALIV " +
                            "y Alba Lab y descubre alternativas para colaborar " +
                            "mediante productos, servicios o iniciativas " +
                            "de responsabilidad social.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (esOscuro) Color(0xFFE0F2E9) else textoPrincipal,
                    lineHeight = 22.sp
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colorTarjeta)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "El cambio también comienza contigo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
                Text(
                    text = "Infórmate sobre los programas y descubre " +
                            "maneras de aportar a esta iniciativa.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoPrincipal,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun TarjetaImpacto(
    simbolo: String,
    titulo: String,
    descripcion: String,
    colorTarjeta: Color,
    colorIcono: Color,
    colorTitulo: Color,
    colorTexto: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = colorTarjeta),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(colorIcono, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = simbolo, fontSize = 24.sp)
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = colorTitulo
                )
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorTexto,
                    lineHeight = 21.sp
                )
            }
        }
    }
}
