
package com.example.corpaliv.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val VerdeCarga = Color(0xFF176B50)
private val FondoCarga = Color(0xFFF5F7F4)
private val VerdeClaroCarga = Color(0xFFE4F1E9)

@Composable
fun PantallaCargaScreen(
    onComenzar: () -> Unit
) {
    var mostrarMensaje by remember {
        mutableStateOf(false)
    }

    val transicion = rememberInfiniteTransition(
        label = "animacionCorazon"
    )

    val escala by transicion.animateFloat(
        initialValue = 1f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "escalaCorazon"
    )

    var mostrarContenido by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoCarga)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CORPALIV",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                color = VerdeCarga
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Inclusión que transforma vidas",
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(38.dp))

            // Corazón interactivo y animado
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        color = VerdeClaroCarga,
                        shape = CircleShape
                    )
                    .clickable {
                        mostrarMensaje = !mostrarMensaje
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (mostrarMensaje) "💚" else "💚",
                    fontSize = 76.sp,
                    modifier = Modifier.scale(escala)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Toca el corazón",
                fontSize = 14.sp,
                color = VerdeCarga,
                fontWeight = FontWeight.SemiBold
            )

            AnimatedVisibility(
                visible = mostrarMensaje,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = { it / 3 }
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "Cada persona tiene capacidades, sueños y talentos. " +
                                "La inclusión nos ayuda a construir oportunidades para todos. 🌱",
                        modifier = Modifier.padding(18.dp),
                        textAlign = TextAlign.Center,
                        color = VerdeCarga,
                        fontSize = 15.sp,
                        lineHeight = 23.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            Text(
                text = "Conoce nuestros programas y descubre cómo colaborar.",
                fontSize = 15.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onComenzar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeCarga
                )
            ) {
                Text(
                    text = "Comenzar  →",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Juntos construimos una sociedad más inclusiva",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
