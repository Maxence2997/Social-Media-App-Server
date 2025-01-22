package idv.maxence.idv.maxence2997.socialmediaapp.config

import io.ktor.server.config.*

object HashConfig {
  lateinit var hashKey: String
  lateinit var algorithm: String
  fun load(config: ApplicationConfig) {
    hashKey = config.property("hash.hashKey").getString()
    algorithm = config.property("hash.algorithm").getString()
  }
}