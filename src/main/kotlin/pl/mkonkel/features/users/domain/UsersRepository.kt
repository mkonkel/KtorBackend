package pl.mkonkel.features.users.domain

import pl.mkonkel.features.users.data.User
import pl.mkonkel.features.users.data.UserRequest

interface UsersRepository {
    suspend fun addUser(userRequest: UserRequest): User?
    suspend fun getUsers(): List<User>

    suspend fun getUserByUsernameAndPassword(username: String, password: String) : User?
    suspend fun exists(name: String): Boolean
}