package com.iagogf.pipipoti.data.sqlite

//BD con SQLite, -- ( La usaremos para las melodías de ir al baño )

import android.provider.BaseColumns

object MelodyDBContract{

    //Aquí el nombre y versión de la base de datos
    const val DATABASE_NAME = "MelodySQLiteDB"
    const val DATABASE_VERSION = 1


    // Definimos la tabla de melodías
    object Melodies : BaseColumns {
        const val TABLE_NAME = "Melodies"
        const val COLUMN_NAME = "name" // Nombre de la melodía
        const val COLUMN_FILE_PATH = "file_path" // Ruta del archivo en res/raw
        const val COLUMN_DURATION = "duration" // Duración en segundos
    }

    // Creamos la tabla de la BD (esquema de la tabla) - Se lo pasamos al Helper en el onCreate
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${Melodies.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${Melodies.COLUMN_NAME} TEXT," +
                "${Melodies.COLUMN_FILE_PATH} TEXT," +
                "${Melodies.COLUMN_DURATION} INTEGER)"

    // Insertamos las melodías iniciales en la tabla
    const val SQL_INSERT_MELODIES = """
        INSERT INTO ${Melodies.TABLE_NAME} 
        (${Melodies.COLUMN_NAME}, ${Melodies.COLUMN_FILE_PATH}, ${Melodies.COLUMN_DURATION}) VALUES
        ('Melodía de 2 minutos', 'android.resource://com.iagogf.pipipoti/raw/melody_2min', 120),
        ('Melodía de 3 minutos y medio', 'android.resource://com.iagogf.pipipoti/raw/melody_3min30', 210),
        ('Melodía de 5 minutos', 'android.resource://com.iagogf.pipipoti/raw/melody_5min', 300)
    """

    // Comando para borrar la tabla
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Melodies.TABLE_NAME}"

}