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

const val KAKAO_URL = "https://kapi.kakao.com/v2/user/me"
const val DEFAULT_COUNT = 10

fun Route.userRoute(dao: DAOFacade, client: HttpClient) {
    post("user") {
        val token = call.request.header("token")

        val user: KakaoUser = client.get(KAKAO_URL) {
            headers { append(HttpHeaders.Authorization, "Bearer $token") }
        }.body()

        with(user) {
            dao.insertUser(
                nickname = properties.nickname,
                email = kakaoAccount.email,
                thumbnail = properties.thumbnailImage
            )
            call.respond(HttpStatusCode.Created)
        }
    }

    get("user/rank") {
        val count = call.request.queryParameters["count"]?.toIntOrNull() ?: DEFAULT_COUNT
        val users = dao.getTopUsers(count)

        call.respond(HttpStatusCode.OK, "users" to users)
    }
}