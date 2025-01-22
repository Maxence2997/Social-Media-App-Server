package idv.maxence

import idv.maxence.idv.maxence2997.socialmediaapp.config.DatabaseFactory
import idv.maxence.idv.maxence2997.socialmediaapp.configureRouting
import idv.maxence.idv.maxence2997.socialmediaapp.configureSecurity
import idv.maxence.idv.maxence2997.socialmediaapp.configureSerialization
import idv.maxence.idv.maxence2997.socialmediaapp.di.configureDI
import io.ktor.server.application.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
  DatabaseFactory.init()
  configureSecurity()
  configureSerialization()
  configureDI()
  configureRouting()
}
