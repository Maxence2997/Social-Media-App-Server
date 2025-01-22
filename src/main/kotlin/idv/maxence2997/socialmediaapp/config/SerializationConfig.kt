package idv.maxence.idv.maxence2997.socialmediaapp.config

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
  install(ContentNegotiation) {
    json(
      Json {
        ignoreUnknownKeys = true
        isLenient = true
      }
    )
  }
}