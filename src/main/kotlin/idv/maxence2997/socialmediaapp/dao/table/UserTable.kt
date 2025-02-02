package idv.maxence.idv.maxence2997.socialmediaapp.dao.entity

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
  val id = integer("id").autoIncrement()
  val username = varchar("username", length = 255)
  val email = varchar("email", length = 255)
  val password = varchar("password", length = 255)
  val bio = varchar("bio", length = 255).default(
    "Hey there! I'm using Social Media App! Welcome to my profile!"
  )
  val avatar = text("avatar").nullable()
  override val primaryKey: PrimaryKey = PrimaryKey(id)
}