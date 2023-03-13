package com.debattle.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Article(
    val articleId: Int,
    val content: String,
    val author: String,
    val likes: Int,
    val agreement: Boolean
)

object Articles : Table() {
    val articleId = integer("article_id").autoIncrement()
    val content = varchar("content", 256)
    val author = varchar("author", 200).references(Users.nickname)
    val like = integer("like")
    val agreement = bool("agreement")

    override val primaryKey = PrimaryKey(articleId)
}

