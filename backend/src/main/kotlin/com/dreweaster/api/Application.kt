package com.dreweaster.api

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import com.dreweaster.api.config.CorsConfig
import com.dreweaster.api.config.JwtConfig
import com.google.inject.Guice
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.module() {
    Guice.createInjector(ApplicationModule(this))
}

fun Application.configureCors(config: CorsConfig) {
    install(CORS) {
        host(config.allowedHost)
        header(HttpHeaders.Authorization)
    }
}

fun Application.configureJwtAuthentication(config: JwtConfig) {
    install(Authentication) {
        jwt {
            realm = config.realm
            verifier(makeJwkProvider(config.issuer), config.issuer)
            validate { credential ->
                when {
                    credential.payload.audience.contains(config.audience) -> JWTPrincipal(credential.payload)
                    else -> null
                }
            }
        }
    }
}

private fun makeJwkProvider(issuer: String): JwkProvider = JwkProviderBuilder(issuer)
        .cached(10, 24, TimeUnit.HOURS)
        .rateLimited(10, 1, TimeUnit.MINUTES)
        .build()