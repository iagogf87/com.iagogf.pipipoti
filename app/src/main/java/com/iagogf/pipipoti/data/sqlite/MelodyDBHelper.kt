package com.iagogf.pipipoti.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract.DATABASE_NAME
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract.DATABASE_VERSION
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract.SQL_CREATE_ENTRIES
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract.SQL_DELETE_ENTRIES
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract.SQL_INSERT_MELODIES

// Helper para gestionar la base de datos SQLite para melodías
class MelodyDBHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    // Se llama cuando se crea la base de datos por primera vez
    override fun onCreate(db: SQLiteDatabase) {
        // Crear la tabla de melodías
        db.execSQL(SQL_CREATE_ENTRIES)
        // Insertar melodías iniciales
        db.execSQL(SQL_INSERT_MELODIES)
    }

    //Para actualizarla primero la borra y después la crea actualizada
    // Se llama cuando se actualiza la versión de la base de datos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Borrar la tabla existente
        db?.execSQL(SQL_DELETE_ENTRIES)
        // Crear de nuevo la tabla con las actualizaciones
        if (db != null) {
            onCreate(db)
        }
    }
}