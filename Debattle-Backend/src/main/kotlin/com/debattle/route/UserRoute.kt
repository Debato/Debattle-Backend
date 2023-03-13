package com.debattle.route

import com.debattle.dao.DAOFacade
import com.debattle.model.KakaoUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val kakaoUrl = "https://kapi.kakao.com/v2/user/me"
fun Route.userRoute(dao: DAOFacade, client: HttpClient) {
    post("user") {
        val token = call.request.header("token")

        val user: KakaoUser = client.get(kakaoUrl) {
            headers { append(HttpHeaders.Authorization, "Bearer $token") }
        }.body()

        with(user) {
            dao.insertUser(properties.nickname, properties.thumbnailImage, kakaoAccount.email)
            call.respond(HttpStatusCode.Created)
        }
    }
}