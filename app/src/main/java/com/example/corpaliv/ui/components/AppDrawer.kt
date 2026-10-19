package com.example.corpaliv.ui.components

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.corpaliv.R

@Composable
fun AppDrawer(
    pantallaActual: String,
    onPantallaSeleccionada: (String) -> Unit
) {
    val configuracion = LocalConfiguration.current
    val colores = MaterialTheme.colorScheme
    val modoOscuro = colores.background == Color(0xFF121212)
    val altoContraste = colores.background == Color.White && colores.onBackground == Color.Black

    val fondoMenu = when {
        modoOscuro -> Color(0xFF121212)
        altoContraste -> Color.White
        else -> Color(0xFFF5F7F4)
    }
    val verdeMenu = when {
        modoOscuro -> Color(0xFF66D6A5)
        altoContraste -> Color(0xFF003D2B)
        else -> Color(0xFF176B50)
    }
    val verdeSeleccionado = when {
        modoOscuro -> Color(0xFF263A33)
        altoContraste -> Color(0xFFE8F3EE)
        else -> Color(0xFFDCEFE4)
    }
    val textoMenu = colores.onSurface
    val textoSecundario = colores.onSurfaceVariant
    val horizontal = configuracion.screenWidthDp > configuracion.screenHeightDp

    val opciones = listOf(
        "Inicio" to "🏠",
        "Programas" to "📚",
        "Impacto social" to "💚",
        "Colaborar" to "🤝",
        "Contacto" to "✉️",
        "Accesibilidad" to "♿"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoMenu)
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp,
                vertical = if (horizontal) 8.dp else 18.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colores.surface, RoundedCornerShape(20.dp))
                .padding(
                    horizontal = 16.dp,
                    vertical = if (horizontal) 8.dp else 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (horizontal) 5.dp else 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_corpaliv),
                contentDescription = "Logo oficial de CORPALIV",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (horizontal) 42.dp else 76.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Inclusión que transforma vidas",
                fontSize = if (horizontal) 12.sp else 14.sp,
                color = verdeMenu,
                fontWeight = FontWeight.Medium
            )

            HorizontalDivider(color = verdeMenu.copy(alpha = 0.18f))

            Text(
                text = "EXPLORA CORPALIV",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = verdeMenu,
                letterSpacing = 1.5.sp
            )
        }

        Spacer(modifier = Modifier.height(if (horizontal) 10.dp else 22.dp))

        Text(
            text = "MENÚ PRINCIPAL",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = textoSecundario,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(
                start = 12.dp,
                bottom = if (horizontal) 4.dp else 10.dp
            )
        )

        opciones.forEach { (opcion, emoji) ->
            val seleccionada = pantallaActual == opcion

            NavigationDrawerItem(
                label = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(text = emoji, fontSize = 20.sp)
                        Text(
                            text = opcion,
                            fontSize = 15.sp,
                            fontWeight = if (seleccionada) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                },
                selected = seleccionada,
                onClick = { onPantallaSeleccionada(opcion) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = if (horizontal) 1.dp else 4.dp),
                shape = RoundedCornerShape(14.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = verdeSeleccionado,
                    unselectedContainerColor = Color.Transparent,
                    selectedTextColor = verdeMenu,
                    unselectedTextColor = textoMenu
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = verdeMenu.copy(alpha = 0.18f))
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "🌱", fontSize = 17.sp)
            Text(
                text = "Juntos construimos una sociedad más inclusiva",
                fontSize = 12.sp,
                color = textoSecundario,
                lineHeight = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
