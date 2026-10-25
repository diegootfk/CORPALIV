# CORPALIV — Inclusión que transforma vidas

## Integrantes

- Diego Berríos
- Valentina Osses

## Descripción del proyecto

CORPALIV es una aplicación móvil desarrollada como parte de la asignatura **Desarrollo de Aplicaciones Móviles**. Su objetivo es dar a conocer la labor de CORPALIV, promover la inclusión de personas con discapacidad múltiple y acercar a la comunidad a sus programas y oportunidades de colaboración.

La aplicación presenta una interfaz intuitiva, accesible y coherente con la identidad visual de la organización.

## Objetivo general

Desarrollar una aplicación móvil que permita conocer los programas de CORPALIV, comprender su labor social y facilitar la participación de personas interesadas en colaborar con la organización.

## Funcionalidades principales

- **Inicio:** presentación de CORPALIV y accesos a las principales secciones de la aplicación.
- **Programas:** información sobre la Escuela Especial Jan Van Dijk y la Red de Inclusión Sociolaboral Alba Lab.
- **Impacto social:** contenido relacionado con la inclusión y el trabajo de la organización.
- **Colaborar:** selección de distintas causas y formulario para registrar solicitudes de aporte.
- **Contacto:** formulario para registrar consultas, con opción de tomar una fotografía con la cámara o seleccionar una imagen de la galería.
- **Accesibilidad:** opciones para ajustar el tamaño del texto y el contraste visual.
- **Almacenamiento local:** persistencia de información mediante Room.
- **Notificaciones locales:** avisos relacionados con eventos relevantes de la aplicación.
- **Animaciones:** elementos animados para mejorar la interacción y la experiencia de usuario.
- **Navegación:** acceso a las diferentes secciones mediante un menú de navegación.

## Tecnologías utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Arquitectura MVVM
- Room para la persistencia de datos
- Kotlin Coroutines
- Git y GitHub para el control de versiones
- API de Android para el uso de la cámara, selección de imágenes y notificaciones locales.

## Arquitectura del proyecto

El proyecto está organizado de forma modular para separar la interfaz, la lógica de presentación y el almacenamiento de datos.

Principales paquetes:

- `ui.screens`: contiene las pantallas de la aplicación.
- `ui.components`: contiene componentes reutilizables de interfaz.
- `ui.viewmodel`: contiene los ViewModels y la lógica de presentación.
- `data.local`: contiene las entidades, DAO y base de datos local mediante Room.

Esta organización facilita el mantenimiento y la ampliación de las funcionalidades.

## Requisitos para ejecutar el proyecto

- Android Studio.
- JDK compatible con la configuración del proyecto.
- Android SDK instalado.
- Emulador Android o dispositivo físico compatible.

## Permisos y recursos del dispositivo

La aplicación integra funciones que utilizan recursos nativos del dispositivo, como la cámara, el selector de imágenes y las notificaciones locales.

Los permisos se solicitan cuando corresponde y el funcionamiento de las características que los requieren depende de la autorización otorgada por la persona usuaria y de la versión de Android.

## Repositorio

https://github.com/diegootfk/CORPALIV

## Contexto académico

Proyecto desarrollado para la asignatura **Desarrollo de Aplicaciones Móviles**, como propuesta tecnológica orientada a la difusión de los programas de CORPALIV y la promoción de la inclusión social.

## Autores

**Diego Berríos y Valentina Osses**
