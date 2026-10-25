
package com.example.corpaliv.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Solicitud::class, Contacto::class],
    version = 6,
    exportSchema = false
)
abstract class SolicitudDatabase : RoomDatabase() {

    abstract fun solicitudDao(): SolicitudDao
    abstract fun contactoDao(): ContactoDao

    companion object {

        private val MIGRACION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE solicitudes " +
                            "ADD COLUMN anonima INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        private val MIGRACION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS contactos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        nombre TEXT NOT NULL,
                        correo TEXT NOT NULL,
                        organizacion TEXT NOT NULL,
                        motivo TEXT NOT NULL,
                        mensaje TEXT NOT NULL,
                        fechaCreacion TEXT NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        private val MIGRACION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE solicitudes " +
                            "ADD COLUMN rut TEXT NOT NULL DEFAULT ''"
                )
            }
        }

        private val MIGRACION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE solicitudes " +
                            "ADD COLUMN causa TEXT NOT NULL DEFAULT 'Donación general'"
                )
            }
        }

        private val MIGRACION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE solicitudes " +
                            "ADD COLUMN apellidos TEXT NOT NULL DEFAULT ''"
                )
                database.execSQL(
                    "ALTER TABLE solicitudes " +
                            "ADD COLUMN aporteMensual INTEGER NOT NULL DEFAULT 0"
                )
                database.execSQL(
                    "ALTER TABLE solicitudes " +
                            "ADD COLUMN metodoPago TEXT NOT NULL DEFAULT 'Sin seleccionar'"
                )
            }
        }

        @Volatile
        private var INSTANCIA: SolicitudDatabase? = null

        fun obtenerBaseDatos(context: Context): SolicitudDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    SolicitudDatabase::class.java,
                    "corpaliv_db"
                )
                    .addMigrations(
                        MIGRACION_1_2,
                        MIGRACION_2_3,
                        MIGRACION_3_4,
                        MIGRACION_4_5,
                        MIGRACION_5_6
                    )
                    .build()

                INSTANCIA = instancia
                instancia
            }
        }
    }
}
