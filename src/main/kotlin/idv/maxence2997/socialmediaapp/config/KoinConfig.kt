package idv.maxence.idv.maxence2997.socialmediaapp.config

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
  install(Koin) {
    modules(
      appModule
    )
  }
}