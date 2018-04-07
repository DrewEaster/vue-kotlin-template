package com.dreweaster.api.util

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import com.dreweaster.api.config.JwtConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import java.util.concurrent.TimeUnit

interface JwtSupport {

    fun configureJwtAuthentication(application: Application, config: JwtConfig) {

        application.install(Authentication) {
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
}