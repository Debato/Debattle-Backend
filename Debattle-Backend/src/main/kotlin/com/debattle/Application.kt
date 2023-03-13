package com.debattle

import com.debattle.dao.DatabaseFactory
import io.ktor.server.application.*
import com.debattle.plugins.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    installKoin()
    DatabaseFactory.init(environment.config)
    configureSerialization()
    configureRouting()
}
