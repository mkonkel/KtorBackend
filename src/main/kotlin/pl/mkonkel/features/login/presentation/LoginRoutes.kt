package pl.mkonkel.features.login.presentation

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlinx.datetime.Clock
import org.koin.ktor.ext.inject
import pl.mkonkel.features.login.data.LoginRequest
import pl.mkonkel.features.login.data.LoginResponse
import pl.mkonkel.features.users.domain.UsersRepository
import pl.mkonkel.plugins.AUDIENCE
import pl.mkonkel.plugins.ISSUER
import pl.mkonkel.plugins.SECRET
import java.util.*

fun Route.loginRouting() {
    val repo by inject<UsersRepository>()

    route("/login") {
        post {
            val loginRequest = call.receive<LoginRequest>()

            repo.getUserByUsernameAndPassword(loginRequest.username, loginRequest.password)
                ?.let {
                    val token = JWT.create()
                        .withAudience(AUDIENCE)
                        .withIssuer(ISSUER)
                        .withClaim("userId", it.id)
                        .withExpiresAt(Date(Clock.System.now().toEpochMilliseconds() + 60000))
                        .sign(Algorithm.HMAC256(SECRET))

                    call.respond(LoginResponse(token))
                } ?: call.respondText(
                status = HttpStatusCode.BadRequest,
                text = "Invalid login or password"
            )

        }
    }
}