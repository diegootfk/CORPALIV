# 💚 CORPALIV — Inclusión que transforma vidas

Aplicación móvil desarrollada en **Kotlin y Jetpack Compose** para dar a conocer la labor de CORPALIV, sus programas de inclusión educativa y sociolaboral, su impacto social y las formas de colaboración.

El proyecto busca ofrecer una experiencia accesible, intuitiva y visualmente coherente, facilitando el acceso a información sobre la corporación y sus iniciativas.

## 📱 Descripción del proyecto

CORPALIV es una aplicación Android que permite a las personas conocer los programas de la corporación, explorar información sobre su impacto social, registrar su interés en colaborar y contactar a la organización.

La aplicación incorpora opciones de accesibilidad y funcionalidades nativas de Android para mejorar la experiencia de uso.

## ✨ Funcionalidades principales

- **Inicio:** presentación de CORPALIV y acceso a las principales secciones.
- **Programas:** información sobre la Escuela Especial Jan Van Dijk y la Red de Inclusión Sociolaboral Alba Lab.
- **Impacto social:** contenido relacionado con la labor y el impacto social de la corporación.
- **Colaborar:** formulario para registrar el interés en realizar aportes solidarios.
- **Contacto:** formulario para registrar consultas y solicitudes.
- **Accesibilidad:** ajuste del tamaño del texto, modo oscuro y alto contraste.
- **Navegación:** menú lateral para acceder a las distintas pantallas.
- **Compartir:** posibilidad de compartir información de CORPALIV mediante aplicaciones compatibles.
- **Persistencia local:** almacenamiento de información mediante Room.
- **Notificaciones locales:** notificaciones relacionadas con operaciones de la aplicación.
- **Cámara:** funcionalidad para capturar imágenes desde la sección de contacto.

> **Nota:** los formularios guardan información localmente. El registro de una solicitud en el dispositivo no significa que esta se haya enviado automáticamente a CORPALIV, ya que eso requiere una integración con un servicio externo.

## 🛠️ Tecnologías utilizadas

- **Lenguaje:** Kotlin.
- **Interfaz de usuario:** Jetpack Compose.
- **Diseño visual:** Material Design 3.
- **Arquitectura:** Model-View-ViewModel (MVVM).
- **Persistencia local:** Room.
- **Gestión de estado:** State de Compose y ViewModels.
- **Control de versiones:** Git y GitHub.
- **Entorno de desarrollo:** Android Studio.
- **Plataforma:** Android.

## 📂 Estructura del proyecto

```text
CORPALIV/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/corpaliv/
│           │   ├── data/
│           │   │   └── local/
│           │   ├── ui/
│           │   │   ├── components/
│           │   │   ├── screens/
│           │   │   ├── theme/
│           │   │   └── viewmodel/
│           │   ├── App.kt
│           │   ├── MainActivity.kt
│           │   └── NotificacionesLocales.kt
│           └── res/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── README.md
```

### Organización de los componentes

- `ui/screens/`: contiene las pantallas de la aplicación.
- `ui/components/`: contiene componentes reutilizables, como el menú lateral y la barra superior.
- `ui/theme/`: define los colores y la tipografía.
- `data/local/`: contiene los componentes relacionados con la persistencia local.
- `ui/viewmodel/`: gestiona el estado y la lógica de los formularios.
- `App.kt`: coordina la navegación entre las pantallas.

## ⚙️ Requisitos

Para ejecutar el proyecto se recomienda disponer de:

- Android Studio.
- JDK compatible con la configuración Gradle del proyecto.
- Android SDK instalado.
- Un emulador Android o un dispositivo físico con depuración USB habilitada.

Las versiones específicas de Gradle, Kotlin y Android Gradle Plugin se encuentran definidas en los archivos de configuración del proyecto.

## 🚀 Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/diegootfk/CORPALIV.git
```

### 2. Entrar al directorio

```bash
cd CORPALIV
```

### 3. Abrir el proyecto

Abre la carpeta del proyecto desde Android Studio y espera a que termine la sincronización de Gradle.

### 4. Ejecutar la aplicación

Selecciona un emulador Android o conecta un dispositivo físico y pulsa **Run ▶**.

También puedes compilar desde la terminal:

En Windows:

```bash
gradlew.bat assembleDebug
```

En macOS o Linux:

```bash
./gradlew assembleDebug
```

## ♿ Accesibilidad

La aplicación incluye opciones para personalizar la experiencia de uso:

- Ajustar el tamaño del texto.
- Activar el modo oscuro.
- Activar el modo de alto contraste.

Estas opciones buscan facilitar la lectura y mejorar la visualización de la interfaz.

## 🔐 Privacidad y almacenamiento

La aplicación utiliza almacenamiento local para registrar información de sus formularios. Las solicitudes no se envían automáticamente a un servidor.

No se deben subir al repositorio credenciales, claves privadas, archivos locales de configuración ni otros datos sensibles.

Las fotografías utilizadas en el proyecto deben contar con las autorizaciones necesarias para su publicación.

## 🌿 Propósito social

CORPALIV busca contribuir a una sociedad más inclusiva, visibilizando oportunidades de participación y colaboración en iniciativas de inclusión educativa y sociolaboral.

**Inclusión que transforma vidas.**

## 👥 Integrantes

- **Diego Berríos**
- **Valentina Osses**

