package pl.mkonkel.plugins

import io.ktor.server.application.Application
import org.koin.ktor.ext.inject
import pl.mkonkel.database.DatabaseFactory

fun Application.configureDatabase() {
    val databaseFactory by inject<DatabaseFactory>()
    databaseFactory.create()
}
