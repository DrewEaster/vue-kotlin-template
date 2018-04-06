package com.dreweaster.api

import com.dreweaster.api.config.CorsConfig
import com.dreweaster.api.config.JwtConfig
import com.dreweaster.api.config.KtorCorsConfig
import com.dreweaster.api.config.KtorJwtConfig
import com.google.inject.AbstractModule
import io.ktor.application.Application
import io.ktor.config.ApplicationConfig

class ApplicationModule(val application: Application) : AbstractModule() {

    override fun configure() {

        bind(Application::class.java).toInstance(application)

        // Configuration
        bind(ApplicationConfig::class.java).toInstance(application.environment.config)
        bind(JwtConfig::class.java).to(KtorJwtConfig::class.java)
        bind(CorsConfig::class.java).to(KtorCorsConfig::class.java)

        // Routes
        bind(ApplicationRoutes::class.java).asEagerSingleton()
    }
}