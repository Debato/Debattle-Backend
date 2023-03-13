package com.debattle.dao

import com.debattle.dao.DatabaseFactory.dbQuery
import com.debattle.model.Users
import org.jetbrains.exposed.sql.insert

class DAOFacadeImpl: DAOFacade {
    override suspend fun insertUser(nickname: String, email: String, thumbnail: String): Unit = dbQuery {
        Users.insert {
            it[Users.nickname] = nickname
            it[Users.email] = email
            it[Users.thumbnail] = thumbnail
        }
    }
}