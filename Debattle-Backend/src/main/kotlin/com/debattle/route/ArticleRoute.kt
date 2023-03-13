package com.debattle.route

import com.debattle.dao.DAOFacade
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.articleRoute(dao: DAOFacade) {
    get("article") {
        val articles = dao.getAllArticles()

        call.respond(HttpStatusCode.OK, "articles" to articles)
    }

    get("article/{id}") {
        val articleId = call.parameters["articleId"]?.toIntOrNull()

        articleId?.let {
            val article = dao.getArticleById(it)
            call.respond(HttpStatusCode.OK, "article" to article)
        } ?: call.respondRedirect("article")
    }

    post("article") {
        val params = call.receiveParameters()
        val content = params["content"].toString()
        val author = params["author"].toString()
        val agreement = params["agreement"].toBoolean()

        dao.postArticle(content, author, agreement)

        call.respond(HttpStatusCode.OK)
    }
}