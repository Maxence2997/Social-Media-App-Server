package idv.maxence.idv.maxence2997.socialmediaapp.route

import idv.maxence.idv.maxence2997.socialmediaapp.business.AuthBusinessService
import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponse
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignUpParams
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import org.koin.ktor.ext.inject

fun Routing.authRouting() {
  val businessService by inject<AuthBusinessService>()
  route("/auth") {
    post("/sign-up") {
      val params = call.receiveNullable<SignUpParams>(
        typeInfo = TypeInfo(SignUpParams::class)
      ) ?: return@post call.respond(
        message = AuthResponse(
          errorMessage = "Invalid request",
        ),
        typeInfo = TypeInfo(AuthResponse::class)
      )
      val response = businessService.signUp(params)

      call.respond(
        message = response.data,
        typeInfo = TypeInfo(AuthResponse::class)
      )
    }
  }
}