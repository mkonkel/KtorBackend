package pl.mkonkel.features.users.domain

import pl.mkonkel.features.users.data.User
import java.util.*

class UsersRepositoryImpl : UsersRepository {
    private val users: MutableList<User> = mutableListOf()

    override fun addUser(name: String): User {
        require(users.none { it.name == name })

        return User(
            id = UUID.randomUUID().toString(),
            name = name
        )
            .also { users.add(it) }
    }
}