package pl.mkonkel.features.users.domain

import pl.mkonkel.features.users.data.User
import pl.mkonkel.features.users.data.dao.UsersDAOFacade
import java.util.*

class UsersRepositoryImpl(private val dao: UsersDAOFacade) : UsersRepository {

    override suspend fun addUser(name: String): User? {
        return dao.createUser(name)
    }

    override suspend fun getUsers(): List<User> {
        return dao.getUsers()
    }
}