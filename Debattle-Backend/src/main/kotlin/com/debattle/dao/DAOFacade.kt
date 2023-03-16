package com.debattle.dao

import com.debattle.model.Article

interface DAOFacade {
    suspend fun insertUser(nickname: String, email: String, thumbnail: String)

    suspend fun getAllArticles(): List<Article>

    suspend fun getArticleById(articleId: Int): Article?

    suspend fun postArticle(title: String, content: String, agreement: Boolean)

    suspend fun updateLikes(articleId: Int)

    suspend fun getTopArticles(count: Int): List<Article>
}