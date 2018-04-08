package com.dreweaster.api.config

import com.google.inject.Inject
import com.google.inject.Singleton
import io.ktor.config.ApplicationConfig

interface JwtConfig {

    val issuer: String

    val audience: String

    val realm: String
}

@Singleton
class KtorJwtConfig @Inject constructor (config: ApplicationConfig) : JwtConfig {

    override val issuer = "https://${config.property("jwt.domain").getString()}/"

    override val audience = config.property("jwt.audience").getString()

    override val realm = config.property("jwt.realm").getString()
}