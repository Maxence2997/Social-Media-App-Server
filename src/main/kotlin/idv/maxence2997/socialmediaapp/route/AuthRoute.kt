package idv.maxence.idv.maxence2997.socialmediaapp.route

import idv.maxence.idv.maxence2997.socialmediaapp.business.AuthBusinessService
import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponse
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignUpParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import org.koin.ktor.ext.inject

fun Routing.authRouting() {
  val businessService by inject<AuthBusinessService>()
  route("/auth") {
    post("/sign-up") {
      val params = call.parsePayload<SignUpParams>()
      val response = businessService.signUp(params)

      call.respond(
        message = response.data, typeInfo = typeInfo<AuthResponse>()
      )
    }
  }
}

suspend inline fun <reified T> ApplicationCall.parsePayload(
  typeInfo: TypeInfo = typeInfo<T>(),
): T {
  return try {
    this.receive<T>(typeInfo)
  } catch (e: Exception) {
    this.respond(
      AuthResponse(errorMessage = "Invalid JSON: ${e.message}"), typeInfo = typeInfo<AuthResponse>()
    )
    throw e // rethrow to prevent further processing
  }
}