package pl.mkonkel

import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.embeddedServer
import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.Locations
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pl.mkonkel.database.DatabaseFactory
import pl.mkonkel.di.appModule
import pl.mkonkel.plugins.configureAuthorization
import pl.mkonkel.plugins.configureDI
import pl.mkonkel.plugins.configureDatabase
import pl.mkonkel.plugins.configureRouting
import pl.mkonkel.plugins.configureSerialization

@OptIn(KtorExperimentalLocationsAPI::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Locations)
        configureDI()
        configureAuthorization()
        configureSerialization()
        configureRouting()
        configureDatabase()
    }.start(wait = true)
}
