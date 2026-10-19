
package com.example.corpaliv

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.example.corpaliv.ui.components.AppDrawer
import com.example.corpaliv.ui.components.AppTopBar
import com.example.corpaliv.ui.screens.AccesibilidadScreen
import com.example.corpaliv.ui.screens.CatalogoScreen
import com.example.corpaliv.ui.screens.ColaborarScreen
import com.example.corpaliv.ui.screens.ContactoScreen
import com.example.corpaliv.ui.screens.HomeScreen
import com.example.corpaliv.ui.screens.PantallaCargaScreen
import com.example.corpaliv.ui.screens.ProgramasScreen
import com.example.corpaliv.ui.theme.CORPALIVTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var mostrarBienvenida by rememberSaveable {
        mutableStateOf(true)
    }

    var pantallaActual by rememberSaveable {
        mutableStateOf("Inicio")
    }

    // Preferencias globales de accesibilidad.
    var escalaTexto by rememberSaveable {
        mutableStateOf(1f)
    }

    var modoContraste by rememberSaveable {
        mutableStateOf("Normal")
    }

    val contexto = LocalContext.current
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    val compartirCorpaliv = {
        val texto = """
            CORPALIV 💚
            Inclusión que transforma vidas.

            Conoce nuestros programas de inclusión educativa y sociolaboral,
            explora nuestro catálogo y descubre cómo colaborar con CORPALIV y Alba Lab.
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
        }

        contexto.startActivity(
            Intent.createChooser(intent, "Compartir CORPALIV")
        )
    }

    CORPALIVTheme(
        darkTheme = modoContraste == "Oscuro",
        highContrast = modoContraste == "Alto contraste"
    ) {
        // Aplica el tamaño de texto a todas las pantallas.
        val densidadBase = LocalDensity.current

        CompositionLocalProvider(
            LocalDensity provides Density(
                density = densidadBase.density,
                fontScale = densidadBase.fontScale * escalaTexto
            )
        ) {
            if (mostrarBienvenida) {
                PantallaCargaScreen(
                    onComenzar = {
                        mostrarBienvenida = false
                    }
                )
            } else {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            AppDrawer(
                                pantallaActual = pantallaActual,
                                onPantallaSeleccionada = { pantalla ->
                                    pantallaActual = pantalla
                                    scope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            AppTopBar(
                                titulo = pantallaActual,
                                onMenuClick = {
                                    scope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        val modifierContenido =
                            Modifier.padding(innerPadding)

                        when (pantallaActual) {
                            "Inicio" -> HomeScreen(
                                modifier = modifierContenido,
                                onProgramasClick = {
                                    pantallaActual = "Programas"
                                },
                                onColaborarClick = {
                                    pantallaActual = "Colaborar"
                                },
                                onCompartirClick = compartirCorpaliv
                            )

                            "Programas" -> ProgramasScreen(
                                modifier = modifierContenido
                            )

                            "Impacto social" -> CatalogoScreen(
                                modifier = modifierContenido
                            )

                            "Colaborar" -> ColaborarScreen(
                                modifier = modifierContenido
                            )

                            "Contacto" -> ContactoScreen(
                                modifier = modifierContenido
                            )

                            "Accesibilidad" -> AccesibilidadScreen(
                                modifier = modifierContenido,
                                escalaTexto = escalaTexto,
                                onEscalaTextoChange = {
                                    escalaTexto = it
                                },
                                modoContraste = modoContraste,
                                onModoContrasteChange = {
                                    modoContraste = it
                                }
                            )

                            else -> HomeScreen(
                                modifier = modifierContenido,
                                onProgramasClick = {
                                    pantallaActual = "Programas"
                                },
                                onColaborarClick = {
                                    pantallaActual = "Colaborar"
                                },
                                onCompartirClick = compartirCorpaliv
                            )
                        }
                    }
                }
            }
        }
    }
}
