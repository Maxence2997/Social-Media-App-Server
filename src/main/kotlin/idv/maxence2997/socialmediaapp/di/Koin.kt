package idv.maxence.idv.maxence2997.socialmediaapp.di

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureDI() {
  install(Koin) {
    modules(
      appModule
    )
  }
}