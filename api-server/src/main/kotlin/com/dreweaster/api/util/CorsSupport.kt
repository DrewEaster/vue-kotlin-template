package com.dreweaster.api.util

import com.dreweaster.api.config.CorsConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders

interface CorsSupport {

    fun configureCors(application: Application, config: CorsConfig) {
        application.install(CORS) {
            host(config.allowedHost)
            header(HttpHeaders.Authorization)
        }
    }
}