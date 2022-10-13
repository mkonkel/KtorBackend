package pl.mkonkel.features.users.data.dao

import pl.mkonkel.features.users.data.User
import pl.mkonkel.features.users.data.UserRequest

interface UsersDAOFacade {
    suspend fun createUser(userRequest: UserRequest): User?
    suspend fun getUsers(): List<User>
    suspend fun getUserByUsernameAndPassword(username: String, password: String): User?
    suspend fun exists(id: String): Boolean
}