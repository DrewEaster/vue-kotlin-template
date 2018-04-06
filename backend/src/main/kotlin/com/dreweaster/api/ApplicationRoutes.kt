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
                        jsonObject(
                            "id" to 1111,
                            "name" to "Startup NYC",
                            "sponsor" to "Alec Pesola",
                            "seedFund" to "500k"
                        ),
                        jsonObject(
                            "id" to 1112,
                            "name" to "Startup Ontario",
                            "sponsor" to "Ryan Chenkie",
                            "seedFund" to "750k"
                        ),
                        jsonObject(
                            "id" to 1113,
                            "name" to "Startup Uttah",
                            "sponsor" to "Diego Poza",
                            "seedFund" to "550k"
                        ),
                        jsonObject(
                            "id" to 1114,
                            "name" to "Startup Australia",
                            "sponsor" to "Eugene Kogan",
                            "seedFund" to "500k"
                        ),
                        jsonObject(
                            "id" to 1115,
                            "name" to "Startup Buenos Aires",
                            "sponsor" to "Sebastian Peyrott",
                            "seedFund" to "600k"
                        ),
                        jsonObject(
                            "id" to 1116,
                            "name" to "Startup Lagos",
                            "sponsor" to "Prosper Otemuyiwa",
                            "seedFund" to "650k"
                        ),
                        jsonObject(
                            "id" to 1117,
                            "name" to "Startup Oslo",
                            "sponsor" to "Mark Fish",
                            "seedFund" to "600k"
                        ),
                        jsonObject(
                            "id" to 1118,
                            "name" to "Startup Calabar",
                            "sponsor" to "Christian Nwamba",
                            "seedFund" to "800k"
                        ),
                        jsonObject(
                            "id" to 1119,
                            "name" to "Startup Nairobi",
                            "sponsor" to "Aniedi Ubong",
                            "seedFund" to "700k"
                        )
                    ))
                }

                authenticate {

                    get("private") {

                        call.respondJson(jsonArray(
                            jsonObject(
                                "id" to 2111,
                                "name" to "Startup Seattle",
                                "sponsor" to "Mark Zuckerberg",
                                "seedFund" to "10M"
                            ),
                            jsonObject(
                                "id" to 2112,
                                "name" to "Startup Vegas",
                                "sponsor" to "Bill Gates",
                                "seedFund" to "20M"
                            ),
                            jsonObject(
                                "id" to 2113,
                                "name" to "Startup Addis-Ababa",
                                "sponsor" to "Aliko Dangote",
                                "seedFund" to "8M"
                            ),
                            jsonObject(
                                "id" to 2114,
                                "name" to "Startup Abuja",
                                "sponsor" to "Femi Otedola",
                                "seedFund" to "5M"
                            ),
                            jsonObject(
                                "id" to 2115,
                                "name" to "Startup Paris",
                                "sponsor" to "Jeff Bezos",
                                "seedFund" to "1.6M"
                            ),
                            jsonObject(
                                "id" to 2116,
                                "name" to "Startup London",
                                "sponsor" to "Dave McClure",
                                "seedFund" to "1M"
                            ),
                            jsonObject(
                                "id" to 2117,
                                "name" to "Startup Oslo",
                                "sponsor" to "Paul Graham",
                                "seedFund" to "2M"
                            ),
                            jsonObject(
                                "id" to 2118,
                                "name" to "Startup Bangkok",
                                "sponsor" to "Jeff Clavier",
                                "seedFund" to "5M"
                            ),
                            jsonObject(
                                "id" to 2119,
                                "name" to "Startup Seoul",
                                "sponsor" to "Paul Buchheit",
                                "seedFund" to "4M"
                            )
                        ))
                    }
                }
            }
        }
    }
}