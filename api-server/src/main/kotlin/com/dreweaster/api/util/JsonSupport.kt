package com.dreweaster.api.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText

interface JsonSupport {

    companion object {
        val gson = Gson()
    }

    suspend fun ApplicationCall.respondJson(obj: JsonElement?, statusCode: HttpStatusCode = HttpStatusCode.OK) =
            if (obj == null) respond(HttpStatusCode.NotFound) else respondText(gson.toJson(obj), ContentType.Application.Json, statusCode)
}