package idv.maxence.idv.maxence2997.socialmediaapp

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain

    authentication {
        jwt {
          val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
          val jwtDomain = this@configureSecurity.environment.config.property("jwt.domain").getString()
          val jwtSecret = this@configureSecurity.environment.config.property("jwt.secret").getString()
          val jwtRealm = this@configureSecurity.environment.config.property("jwt.realm").getString()
            realm = jwtRealm
            verifier(
              JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
