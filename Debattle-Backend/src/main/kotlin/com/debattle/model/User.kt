package com.debattle.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(val userId: Int, val nickname: String, val thumbnail: String)
object Users: Table() {
    val userId = integer("user_id").autoIncrement()
    val nickname = varchar("nickname", 200)
    val email = varchar("email", 320)
    val thumbnail = varchar("thumbnail", 128)

    override val primaryKey = PrimaryKey(userId)
}