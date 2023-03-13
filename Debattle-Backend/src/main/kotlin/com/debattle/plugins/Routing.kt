package com.debattle.plugins

import com.debattle.dao.DAOFacade
import com.debattle.route.articleRoute
import com.debattle.route.userRoute
import io.ktor.client.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val dao by inject<DAOFacade>()
    val client by inject<HttpClient>()

    routing {
        userRoute(dao, client)
        articleRoute(dao)
    }
}
