package com.dreweaster.api

import com.dreweaster.api.config.CorsConfig
import com.dreweaster.api.config.JwtConfig
import com.dreweaster.api.util.CorsSupport
import com.dreweaster.api.util.JsonSupport
import com.dreweaster.api.util.JwtSupport
import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.inject.Inject
import com.google.inject.Singleton
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.auth.*
import io.ktor.routing.route

@Singleton
class ApplicationRoutes @Inject constructor(
        application: Application,
        corsConfig: CorsConfig,
        jwtConfig: JwtConfig): JwtSupport, CorsSupport, JsonSupport {

    init {

        application.configureCors(corsConfig)
        application.configureJwtAuthentication(jwtConfig)
        application.routing {

            route("/api/battles") {

                get("public") {
                    call.respondJson(jsonArray(

                    ))
                }

                authenticate {
                    get("private") {
                        call.respondJson(jsonArray(

                        ))
                    }
                }
            }

            route("/api/albums") {

                get("public") {

                    call.respondJson(jsonArray(
                        jsonObject(
                            "id" to 1,
                            "artist" to "Bruce Springsteen",
                            "title" to "Born to Run"
                        ),
                        jsonObject(
                            "id" to 2,
                            "artist" to "Bob Dylan",
                            "title" to "The Freewheelin' Bob Dylan"
                        ),
                        jsonObject(
                            "id" to 3,
                            "artist" to "Whiskeytown",
                            "title" to "Pneumonia"
                        ),
                        jsonObject(
                            "id" to 4,
                            "artist" to "Josh Ritter",
                            "title" to "The Animal Years"
                        ),
                        jsonObject(
                            "id" to 5,
                            "artist" to "Ryan Adams",
                            "title" to "Heartbreaker"
                        ),
                        jsonObject(
                            "id" to 6,
                            "artist" to "Fleetwood Mac",
                            "title" to "Rumours"
                        ),
                        jsonObject(
                            "id" to 7,
                            "artist" to "Gram Parsons",
                            "title" to "GP"
                        ),
                        jsonObject(
                            "id" to 8,
                            "artist" to "Bon Jovi",
                            "title" to "These Days"
                        )
                    ))
                }

                authenticate {

                    get("private") {

                        call.respondJson(jsonArray(
                            jsonObject(
                                "id" to 9,
                                "artist" to "Spice Girls",
                                "title" to "Spiceworld"
                            ),
                            jsonObject(
                                "id" to 10,
                                "artist" to "Blue",
                                "title" to "All Rise"
                            ),
                            jsonObject(
                                "id" to 11,
                                "artist" to "S Club 7",
                                "title" to "S Club"
                            ),
                            jsonObject(
                                "id" to 12,
                                "artist" to "Westlife",
                                "title" to "Coast to Coast"
                            ),
                            jsonObject(
                                "id" to 13,
                                "artist" to "Five",
                                "title" to "Invincible"
                            ),
                            jsonObject(
                                "id" to 14,
                                "artist" to "Backstreet Boys",
                                "title" to "Backstreet's Back"
                            ),
                            jsonObject(
                                "id" to 15,
                                "artist" to "Girls Aloud",
                                "title" to "Sound of the Underground"
                            ),
                            jsonObject(
                                "id" to 16,
                                "artist" to "One Direction",
                                "title" to "Up All Night"
                            )
                        ))
                    }
                }
            }
        }
    }
}