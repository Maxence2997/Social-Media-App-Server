package idv.maxence.idv.maxence2997.socialmediaapp.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import idv.maxence.idv.maxence2997.socialmediaapp.dao.repository.UserRepository
import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.util.reflect.*
import org.koin.ktor.ext.inject
import java.time.Instant
import java.time.temporal.ChronoUnit

const val CLAIM = "email"
lateinit var jwtAudience: String
lateinit var jwtDomain: String
lateinit var algorithm: Algorithm

fun Application.configureSecurity() {
  val userRepository by inject<UserRepository>()
  jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
  jwtDomain = this@configureSecurity.environment.config.property("jwt.domain").getString()
  val jwtSecret = this@configureSecurity.environment.config.property("jwt.secret").getString()
  algorithm = Algorithm.HMAC256(jwtSecret)
  authentication {
    jwt {
      realm = this@configureSecurity.environment.config.property("jwt.realm").getString()

      verifier(
        JWT.require(algorithm)
          .withAudience(jwtAudience)
          .withIssuer(jwtDomain)
          .build()
      )

      validate { credential ->
        // 提取過期時間
        val expirationTime = credential.payload.expiresAt?.toInstant()

        if (expirationTime == null || expirationTime.isBefore(Instant.now())) {
          // 如果過期時間不存在或已經過期，驗證失敗
          return@validate null
        }

        return@validate credential.payload.getClaim(CLAIM).asString()?.let { email ->
          JWTPrincipal(credential.payload).takeIf { userRepository.findByEmail(email) != null }
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

  return JWT.create()
    .withAudience(jwtAudience)
    .withIssuer(jwtDomain)
    .withClaim(CLAIM, email)
    .withExpiresAt(expiredAt)
    .sign(algorithm)
}
