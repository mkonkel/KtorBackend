package pl.mkonkel.features.users.domain

import pl.mkonkel.features.users.data.User
import pl.mkonkel.features.users.data.UserRequest
import pl.mkonkel.features.users.data.dao.UsersDAOFacade
import java.util.*

class UsersRepositoryImpl(private val dao: UsersDAOFacade) : UsersRepository {

    override suspend fun addUser(userRequest: UserRequest): User? {
        return dao.createUser(userRequest)
    }

    override suspend fun getUsers(): List<User> {
        return dao.getUsers()
    }

    override suspend fun getUserByUsernameAndPassword(username: String, password: String): User? {
        return dao.getUserByUsernameAndPassword(username, password)
    }

    override suspend fun exists(name: String): Boolean {
        return dao.exists(name)
    }
}