package com.debattle

import com.debattle.dao.DatabaseFactory
import io.ktor.server.application.*
import com.debattle.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureSerialization()
}
