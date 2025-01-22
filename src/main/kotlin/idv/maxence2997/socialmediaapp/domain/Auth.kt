package idv.maxence.idv.maxence2997.socialmediaapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class SignUpParams(
  val username: String,
  val password: String,
  val email: String
)

@Serializable
data class SignInParams(
  val email: String,
  val password: String
)

@Serializable
data class AuthResponse(
  val data: AuthResponseData? = null,
  val errorMessage: String? = null
)

@Serializable
data class AuthResponseData(
  val id: Int,
  val token: String,
  val username: String,
  val email: String,
  val bio: String,
  val avatar: String? = null,
  val followerCounts: Int = 0,
  val followingCounts: Int = 0,
)