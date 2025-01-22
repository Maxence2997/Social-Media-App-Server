package idv.maxence.idv.maxence2997.socialmediaapp.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.util.reflect.*
import java.time.Instant
import java.time.temporal.ChronoUnit

const val CLAIM = "email"
lateinit var jwtAudience: String
lateinit var jwtDomain: String
lateinit var algorithm: Algorithm
fun Application.configureSecurity() {
  val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
  val jwtDomain = this@configureSecurity.environment.config.property("jwt.domain").getString()
  val jwtSecret = this@configureSecurity.environment.config.property("jwt.secret").getString()
  val jwtRealm = this@configureSecurity.environment.config.property("jwt.realm").getString()
  val algorithm = Algorithm.HMAC256(jwtSecret)
  authentication {
    jwt {
      realm = jwtRealm

      verifier(
        JWT.require(algorithm).withAudience(jwtAudience).withIssuer(jwtDomain).build()
      )

      validate { credential ->
        return@validate credential.payload.getClaim(CLAIM).asString()?.let {
          JWTPrincipal(credential.payload)
        }
      }

      challenge { _, _ ->
        call.respond(
          status = HttpStatusCode.Unauthorized,
          message = AuthResponse(errorMessage = "Token is not valid or has expired"),
          messageType = typeInfo<AuthResponse>()
        )
      }
    }
  }
}

fun generateToken(email: String): String {
  val expirationHours = 1L
  val expiredAt = Instant.now().plus(expirationHours, ChronoUnit.HOURS)

  return JWT.create().withAudience(jwtAudience).withIssuer(jwtDomain).withClaim(CLAIM, email).withExpiresAt(expiredAt)
    .sign(algorithm)
}
