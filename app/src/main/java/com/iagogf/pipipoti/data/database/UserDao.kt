package com.iagogf.pipipoti.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    // Insertar un usuario en la tabla
    @Insert
    suspend fun insertUser(user: User)

    // Obtener todos los usuarios
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>

    // Verificar si existe un usuario con el nombre proporcionado
    @Query("SELECT EXISTS(SELECT 1 FROM user_table WHERE name = :username)")
    suspend fun doesUserExist(username: String): Boolean

    // Obtener un usuario por su nombre
    @Query("SELECT * FROM user_table WHERE name = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?
}
