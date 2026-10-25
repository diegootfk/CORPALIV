package com.example.corpaliv.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.corpaliv.R

private val VerdeCarga = Color(0xFF176B50)
private val FondoCarga = Color(0xFFF5F7F4)
private val VerdeClaroCarga = Color(0xFFE4F1E9)

@Composable
fun PantallaCargaScreen(
    onComenzar: () -> Unit
) {
    var mostrarMensaje by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoCarga)
            .padding(horizontal = 24.dp, vertical = 28.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_corpaliv),
                contentDescription = "Logo oficial de CORPALIV",
                modifier = Modifier
                    .fillMaxWidth(0.82f)
                    .height(125.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(18.dp))

            Text(
                text = "Inclusión que transforma vidas",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = VerdeCarga,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Acompañamos el desarrollo de personas con discapacidad múltiple, promoviendo oportunidades de aprendizaje, autonomía e inclusión.",
                fontSize = 14.sp,
                color = Color(0xFF59645E),
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )

            Spacer(Modifier.height(26.dp))

            Box(
                modifier = Modifier
                    .size(104.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(VerdeClaroCarga),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "💚",
                    fontSize = 52.sp
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Cada persona importa",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeCarga
            )

            AnimatedVisibility(
                visible = mostrarMensaje,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 3 })
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = "Cada persona tiene capacidades, sueños y talentos. La inclusión nos ayuda a construir oportunidades para todos. 🌱",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = VerdeCarga,
                        fontSize = 14.sp,
                        lineHeight = 21.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Conoce nuestros programas y descubre cómo colaborar.",
                fontSize = 15.sp,
                color = Color(0xFF59645E),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(22.dp))

            Button(
                onClick = onComenzar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VerdeCarga)
            ) {
                Text(
                    text = "Comenzar  →",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Juntos construimos una sociedad más inclusiva",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
