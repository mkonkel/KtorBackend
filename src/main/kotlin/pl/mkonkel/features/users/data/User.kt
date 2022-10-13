package pl.mkonkel.features.users.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val username: String,
)

@Serializable
data class UserRequest(
    val name: String,
    val username: String,
    val password: String
)