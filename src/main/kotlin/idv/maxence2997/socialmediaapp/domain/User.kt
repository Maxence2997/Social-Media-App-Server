package idv.maxence.idv.maxence2997.socialmediaapp.domain

data class User(
  val id: Int,
  val username: String,
  val password: String,
  val email: String,
  val bio: String,
  val avatar: String?
)