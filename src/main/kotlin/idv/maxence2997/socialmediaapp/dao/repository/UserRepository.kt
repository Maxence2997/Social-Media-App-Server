package idv.maxence.idv.maxence2997.socialmediaapp.dao.repository

import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignUpParams
import idv.maxence.idv.maxence2997.socialmediaapp.domain.User

interface UserRepository {
  suspend fun create(createUser: SignUpParams): User
  suspend fun findByEmail(email: String): User?
}