package com.debattle.dao

import com.debattle.dao.DatabaseFactory.dbQuery
import com.debattle.model.Article
import com.debattle.model.Articles
import com.debattle.model.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToArticle(row: ResultRow) = Article(
        articleId = row[Articles.articleId],
        content = row[Articles.content],
        author = row[Articles.author],
        like = row[Articles.like],
        agreement = row[Articles.agreement]
    )

    override suspend fun insertUser(nickname: String, email: String, thumbnail: String): Unit = dbQuery {
        Users.insert {
            it[Users.nickname] = nickname
            it[Users.email] = email
            it[Users.thumbnail] = thumbnail
        }
    }

    override suspend fun getAllArticles(): List<Article> = dbQuery {
        Articles.selectAll()
            .map(::resultRowToArticle)
    }

    override suspend fun getArticleById(articleId: Int): Article? = dbQuery {
        Articles.select { Articles.articleId eq articleId }
            .map(::resultRowToArticle)
            .singleOrNull()
    }
}