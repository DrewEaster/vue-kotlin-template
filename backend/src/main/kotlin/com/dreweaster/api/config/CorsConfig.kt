package com.dreweaster.api.config

import com.google.inject.Inject
import com.google.inject.Singleton
import io.ktor.config.ApplicationConfig

interface CorsConfig {

    val allowedHost: String
}

@Singleton
class KtorCorsConfig @Inject constructor (config: ApplicationConfig) : CorsConfig {

    override val allowedHost = config.property("cors.allowedHost").getString()
}