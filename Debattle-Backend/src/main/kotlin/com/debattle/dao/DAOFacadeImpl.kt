package com.debattle.dao

import com.debattle.dao.DatabaseFactory.dbQuery
import com.debattle.model.Article
import com.debattle.model.Articles
import com.debattle.model.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus

const val INIT_LIKES = 0
const val INCREASE_LIKES = 1

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow) = Article(
        articleId = row[Articles.articleId],
        title = row[Articles.title],
        content = row[Articles.content],
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

    override suspend fun postArticle(title: String, content: String, agreement: Boolean): Unit = dbQuery {
        Articles.insert {
            it[Articles.content] = content
            it[Articles.title] = title
            it[like] = INIT_LIKES
            it[Articles.agreement] = agreement
        }
    }

    override suspend fun updateLikes(articleId: Int): Unit = dbQuery {
        Articles.update({ Articles.articleId eq articleId }) {
            it[like] = like.plus(INCREASE_LIKES)
        }
    }

    override suspend fun getTopArticles(count: Int): List<Article> = dbQuery {
        Articles.selectAll()
            .orderBy(Articles.like, SortOrder.DESC)
            .limit(count)
            .map(::resultRowToArticle)
    }
}