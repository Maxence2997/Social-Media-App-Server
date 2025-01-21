package idv.maxence.idv.maxence2997.socialmediaapp.dao.repository

import idv.maxence.idv.maxence2997.socialmediaapp.config.DatabaseFactory.dbQuery
import idv.maxence.idv.maxence2997.socialmediaapp.dao.entity.UserTable
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignUpParams
import idv.maxence.idv.maxence2997.socialmediaapp.domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class UserRepositoryImpl : UserRepository {
  override suspend fun create(createUser: SignUpParams): User {
    return dbQuery {
      val insertStatement = UserTable.insert {
        it[username] = createUser.username
        it[password] = createUser.password
        it[email] = createUser.email
      }
      insertStatement.resultedValues?.singleOrNull()?.let {
        rowToDomain(it)
      } ?: throw IllegalStateException("No user generated")
    }
  }

  override suspend fun findByEmail(email: String): User? {
    return dbQuery {
      UserTable.selectAll()
        .where { UserTable.email eq email }
        .map { rowToDomain(it) }
        .singleOrNull()
    }
  }

  private fun rowToDomain(row: ResultRow): User = User(
    id = row[UserTable.id],
    username = row[UserTable.username],
    password = row[UserTable.password],
    email = row[UserTable.email],
    bio = row[UserTable.bio],
    avatar = row[UserTable.avatar]
  )
}