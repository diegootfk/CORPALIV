
package com.example.corpaliv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val VerdeAccesibilidad = Color(0xFF176B50)
private val FondoAccesibilidad = Color(0xFFF5F7F4)

@Composable
fun AccesibilidadScreen(
    modifier: Modifier = Modifier,
    escalaTexto: Float,
    onEscalaTextoChange: (Float) -> Unit,
    modoContraste: String,
    onModoContrasteChange: (String) -> Unit
) {
    val oscuro = modoContraste == "Oscuro"
    val altoContraste = modoContraste == "Alto contraste"

    val fondo = when {
        altoContraste -> Color.Black
        oscuro -> Color(0xFF121212)
        else -> FondoAccesibilidad
    }

    val texto = if (oscuro || altoContraste) {
        Color.White
    } else {
        Color(0xFF26332C)
    }

    val tarjeta = when {
        altoContraste -> Color.Black
        oscuro -> Color(0xFF242424)
        else -> Color.White
    }

    val verde = when {
        altoContraste -> Color(0xFFFFFF00)
        oscuro -> Color(0xFF278A68)
        else -> VerdeAccesibilidad
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Accesibilidad",
            fontSize = (26 * escalaTexto).sp,
            fontWeight = FontWeight.Bold,
            color = texto
        )

        Text(
            text = "Personaliza CORPALIV para que sea más cómoda y fácil de usar.",
            fontSize = (16 * escalaTexto).sp,
            color = texto
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = tarjeta
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Tamaño del texto",
                    fontSize = (19 * escalaTexto).sp,
                    fontWeight = FontWeight.Bold,
                    color = texto
                )

                Text(
                    text = "Vista previa del texto",
                    fontSize = (16 * escalaTexto).sp,
                    color = texto
                )

                Text(
                    text = "Tamaño: ${(escalaTexto * 100).toInt()}%",
                    fontSize = (14 * escalaTexto).sp,
                    color = texto
                )

                Slider(
                    value = escalaTexto,
                    onValueChange = onEscalaTextoChange,
                    valueRange = 0.8f..1.6f
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    OutlinedButton(
                        onClick = { onEscalaTextoChange(1f) }
                    ) {
                        Text("Restablecer")
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = tarjeta
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Colores y contraste",
                    fontSize = (19 * escalaTexto).sp,
                    fontWeight = FontWeight.Bold,
                    color = texto
                )

                listOf(
                    "Normal",
                    "Alto contraste",
                    "Oscuro"
                ).forEach { opcion ->
                    val seleccionado = modoContraste == opcion

                    Button(
                        onClick = {
                            onModoContrasteChange(opcion)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (seleccionado) {
                                verde
                            } else if (oscuro) {
                                Color(0xFF424242)
                            } else if (altoContraste) {
                                Color.Black
                            } else {
                                Color.Gray
                            },
                            contentColor = if (
                                altoContraste && seleccionado
                            ) {
                                Color.Black
                            } else {
                                Color.White
                            }
                        )
                    ) {
                        Text(
                            text = opcion,
                            fontSize = (15 * escalaTexto).sp
                        )
                    }
                }
            }
        }
    }
}
