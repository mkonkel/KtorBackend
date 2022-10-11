package pl.mkonkel.database

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import pl.mkonkel.database.tables.Users

class DatabaseFactoryImpl : DatabaseFactory {
    private companion object {
        const val driverClassName = "org.h2.Driver"
        const val jdbcURL = "jdbc:h2:file:./build/db"
    }

    override fun create() {
        Database.connect(jdbcURL, driverClassName)
        SchemaDefinition.createSchema()
    }

    private object SchemaDefinition {
        fun createSchema() {
            transaction {
                SchemaUtils.create(Users)
            }
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }