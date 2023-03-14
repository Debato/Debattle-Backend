package com.debattle.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Article(
    val articleId: Int,
    val title: String,
    val content: String,
    val like: Int,
    val agreement: Boolean
)

object Articles : Table() {
    val articleId = integer("article_id").autoIncrement()
    val title = varchar("title", 20)
    val content = varchar("content", 256)
    val like = integer("like")
    val agreement = bool("agreement")

    override val primaryKey = PrimaryKey(articleId)
}

