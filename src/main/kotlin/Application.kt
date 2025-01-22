package idv.maxence

import idv.maxence.idv.maxence2997.socialmediaapp.config.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
  DatabaseFactory.init()
  HashConfig.load(environment.config)
  configureSecurity()
  configureSerialization()
  configureKoin()
  configureRouting()
}
