package pl.mkonkel.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject
import pl.mkonkel.features.users.domain.UsersRepository

const val SECRET = "SECRET"
const val AUDIENCE = "http://0.0.0.0:8080"
const val ISSUER = "http://0.0.0.0:8080"

fun Application.configureAuthentication() {
    val userRepository by inject<UsersRepository>()

    install(Authentication) {
        jwt("jwt-auth") {
            realm = "GameShopAccess"

            verifier(
                JWT
                    .require(Algorithm.HMAC256(SECRET))
                    .withAudience(AUDIENCE)
                    .withIssuer(ISSUER)
                    .build()
            )

            validate { jwtCredential ->
                jwtCredential.payload.claims["userId"]?.asString()
                    ?.let {
                        if (userRepository.exists(it)) {
                            JWTPrincipal(jwtCredential.payload)
                        } else {
                            null
                        }
                    }
            }

            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired!")
            }
        }
    }
}
