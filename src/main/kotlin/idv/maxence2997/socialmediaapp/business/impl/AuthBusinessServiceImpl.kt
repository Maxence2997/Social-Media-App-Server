package idv.maxence.idv.maxence2997.socialmediaapp.business.impl

import idv.maxence.idv.maxence2997.socialmediaapp.business.AuthBusinessService
import idv.maxence.idv.maxence2997.socialmediaapp.dao.repository.UserRepository
import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponse
import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponseData
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignInParams
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignUpParams
import idv.maxence.idv.maxence2997.socialmediaapp.util.ApiResponse
import io.ktor.http.*

class AuthBusinessServiceImpl(
  private val userRepository: UserRepository,
) : AuthBusinessService {
  override suspend fun signUp(params: SignUpParams): ApiResponse<AuthResponse> {
    return if (userAlreadyExists(params.email)) {
      ApiResponse.Error(
        code = HttpStatusCode.Conflict, data = AuthResponse(
          errorMessage = "User already exists"
        )
      )
    } else {
      val user = userRepository.create(params)
      ApiResponse.Success(
        AuthResponse(
          data = AuthResponseData(
            id = user.id,
            token = "Here is your token",
            email = user.email,
            username = user.username,
            bio = user.bio,
            avatar = user.avatar,
          )
        )
      )
    }
  }

  override suspend fun signIn(params: SignInParams): ApiResponse<AuthResponse> {
    TODO("Not yet implemented")
  }

  private suspend fun userAlreadyExists(email: String): Boolean {
    return userRepository.findByEmail(email) != null
  }
}