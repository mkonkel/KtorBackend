package pl.mkonkel.features.users.domain

import pl.mkonkel.features.users.data.User

interface UsersRepository {
    fun addUser(name: String): User
}