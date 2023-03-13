package com.debattle.dao

import com.debattle.model.Article

interface DAOFacade {
    suspend fun insertUser(nickname: String, email: String, thumbnail: String)

    suspend fun getAllArticles(): List<Article>

    suspend fun getArticleById(articleId: Int): Article?

    suspend fun postArticle(content: String, author: String, agreement: Boolean)
}