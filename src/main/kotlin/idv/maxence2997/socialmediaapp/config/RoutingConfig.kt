package idv.maxence.idv.maxence2997.socialmediaapp.config

import idv.maxence.idv.maxence2997.socialmediaapp.route.authRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
  routing {
    authRouting()
  }
}
