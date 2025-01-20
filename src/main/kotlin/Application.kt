package idv.maxence

import idv.maxence.idv.maxence2997.socialmediaapp.configureRouting
import idv.maxence.idv.maxence2997.socialmediaapp.configureSecurity
import idv.maxence.idv.maxence2997.socialmediaapp.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
}
