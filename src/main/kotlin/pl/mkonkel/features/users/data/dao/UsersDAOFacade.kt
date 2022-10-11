package pl.mkonkel.features.users.data.dao

import pl.mkonkel.features.users.data.User

interface UsersDAOFacade {
    suspend fun createUser(userName: String): User?
    suspend fun getUsers(): List<User>
}