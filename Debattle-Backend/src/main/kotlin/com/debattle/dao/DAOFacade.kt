package com.debattle.dao

import com.debattle.model.Article
import com.debattle.model.User

interface DAOFacade {
    suspend fun insertUser(nickname: String, email: String, thumbnail: String)

    suspend fun getAllArticles(): List<Article>

    suspend fun getArticleById(articleId: Int): Article?

    suspend fun postArticle(title: String, content: String, agreement: Boolean)

    suspend fun updateLikes(articleId: Int)

    suspend fun getTopUsers(count: Int): List<User>
}