package pl.mkonkel.features.users.domain

import pl.mkonkel.features.users.data.User

interface UsersRepository {
    suspend fun addUser(name: String): User?
    suspend fun getUsers(): List<User>
}