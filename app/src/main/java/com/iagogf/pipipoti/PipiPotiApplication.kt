package com.iagogf.pipipoti

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import com.iagogf.pipipoti.data.sqlite.MelodyDBHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PipiPotiApplication: Application()  {

    override fun onCreate() {
        super.onCreate()
        crearBDSqlite()
    }

    //-----Creamos / Abrimos  la base de datos SQLite:-----
    var db: SQLiteDatabase? = null
    private fun crearBDSqlite() {
        val dbhelper =
            MelodyDBHelper(this)  // Instanciamos el Helper // llama al OnCreate / upgrade automático
        db = dbhelper.writableDatabase  // Abrimos la BD en modo escritura
    }

    override fun onTerminate() {  // Cierra la BD al final
        super.onTerminate()
        db?.close()
    }
}
