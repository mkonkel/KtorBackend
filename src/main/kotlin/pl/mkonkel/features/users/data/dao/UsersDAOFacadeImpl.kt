package pl.mkonkel.features.users.data.dao

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import pl.mkonkel.database.dbQuery
import pl.mkonkel.database.tables.Users
import pl.mkonkel.features.users.data.User

class UsersDAOFacadeImpl : UsersDAOFacade {
    override suspend fun createUser(userName: String) = dbQuery {
        Users.insert {
            it[name] = userName
            it[date_created] = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
        }
            .resultedValues?.singleOrNull()?.toUser()
    }

    override suspend fun getUsers(): List<User> = dbQuery {
        Users.selectAll().map { it.toUser() }
    }

    private fun ResultRow.toUser(): User {
        return this.let {
            User(
                id = it[Users.id].toString(),
                name = it[Users.name]
            )
        }
    }
}