package pl.mkonkel.features.users.presentation

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import pl.mkonkel.features.users.data.UserRequest
import pl.mkonkel.features.users.domain.UsersRepository

fun Route.usersRouting() {
    val repo by inject<UsersRepository>()

    route("/users") {
        post {
            val request = call.receive<UserRequest>()
            val user = repo.addUser(request.name)

            call.respond(
                status = HttpStatusCode.Created,
                message = user
            )
        }
    }
}