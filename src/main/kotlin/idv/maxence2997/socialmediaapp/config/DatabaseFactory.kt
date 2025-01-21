package idv.maxence.idv.maxence2997.socialmediaapp.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import idv.maxence.idv.maxence2997.socialmediaapp.dao.entity.UserTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
  fun init() {
    Database.connect(createHikariDataSource())
    transaction {
      SchemaUtils.create(UserTable)
    }
  }

  private fun createHikariDataSource(): HikariDataSource {
    val driverClass = "org.postgresql.Driver"
    val jdbcUrl = "jdbc:postgresql://localhost:5432/social_media_app"
    val password = "123456"
    val hikariConfig = HikariConfig().apply {
      this.driverClassName = driverClass
      this.username = "postgres"
      this.password = password
      this.jdbcUrl = jdbcUrl
      this.maximumPoolSize = 3
      this.isAutoCommit = false
      this.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
      validate()
    }

    return HikariDataSource(hikariConfig)
  }

  suspend fun <T> dbQuery(block: suspend () -> T) =
    newSuspendedTransaction(Dispatchers.IO) { block() }
}