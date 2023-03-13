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
}