package com.iagogf.pipipoti

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract
import com.iagogf.pipipoti.data.sqlite.MelodyDBHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PipiPotiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        crearBDSqlite()
        leerBD()
    }

    // Base de datos SQLite para las melodías
    var db: SQLiteDatabase? = null
    private fun crearBDSqlite() {
        val dbHelper = MelodyDBHelper(this)
        db = dbHelper.writableDatabase // Abrimos la BD en modo escritura
    }

    override fun onTerminate() {
        super.onTerminate()
        db?.close()
    }

    // Leer la base de datos al iniciar
    private fun leerBD() {
        val projection = arrayOf(
            MelodyDBContract.Melodies.COLUMN_NAME,
            MelodyDBContract.Melodies.COLUMN_DURATION
        )

        val cursor = db!!.query(
            MelodyDBContract.Melodies.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (cursor.moveToNext()) {
                val name = getString(getColumnIndexOrThrow(MelodyDBContract.Melodies.COLUMN_NAME))
                val duracion = getString(getColumnIndexOrThrow(MelodyDBContract.Melodies.COLUMN_DURATION))
                Log.d("PipiPotiApplication", "Melodía: $name. Duración: $duracion")
            }
        }
        cursor.close()
    }

    // Función para borrar la base de datos (si es necesario)
    fun borrarBD() {
        this.deleteDatabase(MelodyDBContract.DATABASE_NAME)
        Log.d("PipiPotiApplication", "Base de datos borrada")
    }
}
