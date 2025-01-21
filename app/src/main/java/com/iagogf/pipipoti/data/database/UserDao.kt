package com.iagogf.pipipoti.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PipipotiUsersDao {
    // Obtener todos los usuarios
    @Query("SELECT * FROM PipipotiUsers")
    suspend fun getAllUsers(): List<User>

    // Insertar un usuario en la tabla
    @Insert
    suspend fun insertUser(user: User)

    // Borrar un usuario en la tabla
    @Delete
    suspend fun deleteUser(user: User)

    // Verificar si existe un usuario con el nombre proporcionado
    @Query("SELECT EXISTS(SELECT 1 FROM PipipotiUsers WHERE name = :username)")
    suspend fun doesUserExist(username: String): Boolean

    // Obtener un usuario por su nombre
    @Query("SELECT * FROM PipipotiUsers WHERE name = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?
}
