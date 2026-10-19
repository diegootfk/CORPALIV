
package com.example.corpaliv

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

object NotificacionesLocales {

    private const val CANAL_ID = "corpaliv_notificaciones"
    private const val NOTIFICACION_ID = 1001

    fun crearCanal(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                CANAL_ID,
                "Notificaciones CORPALIV",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Avisos sobre aportes y actividades de CORPALIV"
            }

            val gestor = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            gestor.createNotificationChannel(canal)
        }
    }

    fun enviar(
        context: Context,
        titulo: String,
        mensaje: String
    ): Boolean {
        if (
            Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }

        crearCanal(context)

        val notificacion = NotificationCompat.Builder(context, CANAL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(mensaje)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context)
            .notify(NOTIFICACION_ID, notificacion)

        return true
    }
}
