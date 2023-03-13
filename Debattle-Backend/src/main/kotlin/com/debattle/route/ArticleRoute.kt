package com.debattle.route

import com.debattle.dao.DAOFacade
import io.ktor.http.*
import io.ktor.server.application.*
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
}