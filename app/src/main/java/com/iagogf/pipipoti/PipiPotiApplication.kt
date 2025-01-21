package com.iagogf.pipipoti

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import com.iagogf.pipipoti.data.sqlite.MelodyDBHelper

class PipiPotiApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        crearBD()
    }

    //-----Creamos / Abrimos  la base de datos y sus tablas:-----
    var db: SQLiteDatabase? = null
    private fun crearBD() {
        val dbhelper =
            MelodyDBHelper(this)  //Instanciamos el Helper // llama al OnCreate / o upgrade automatico
        db = dbhelper.writableDatabase  //Abrimos la bd en modo escritura
    }

    override fun onTerminate() {  //Cierra la bd al final
        super.onTerminate()
        db!!.close()
    }
}