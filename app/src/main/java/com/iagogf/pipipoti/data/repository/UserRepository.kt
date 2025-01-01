package com.iagogf.pipipoti.data.repository

import com.iagogf.pipipoti.data.database.User
import com.iagogf.pipipoti.data.database.UserDao

class UserRepository(private val userDao: UserDao) {

    // Insertar un usuario en la base de datos
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    // Obtener todos los usuarios de la base de datos
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    // Verificar si un usuario existe por su nombre
    suspend fun doesUserExist(username: String): Boolean {
        return userDao.doesUserExist(username)
    }

    // Obtener un usuario por su nombre
    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }
}
