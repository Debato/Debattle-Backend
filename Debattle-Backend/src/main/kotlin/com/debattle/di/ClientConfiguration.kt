package com.debattle.di

import com.debattle.dao.DAOFacade
import com.debattle.dao.DAOFacadeImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

val clientModule = module {
    single<DAOFacade> { DAOFacadeImpl() }

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}