package com.debattle.dao

interface DAOFacade {
    suspend fun insertUser(nickname: String, email: String, thumbnail: String)
}