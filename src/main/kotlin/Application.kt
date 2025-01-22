package idv.maxence

import idv.maxence.idv.maxence2997.socialmediaapp.config.DatabaseFactory
import idv.maxence.idv.maxence2997.socialmediaapp.config.configureKoin
import idv.maxence.idv.maxence2997.socialmediaapp.config.configureSerialization
import idv.maxence.idv.maxence2997.socialmediaapp.configureRouting
import idv.maxence.idv.maxence2997.socialmediaapp.configureSecurity
import io.ktor.server.application.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
  DatabaseFactory.init()
  configureSecurity()
  configureSerialization()
  configureKoin()
  configureRouting()
}
