package repository

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import repository.resource.ResourceTable

object DatabaseFactory {
    fun init(
        driver: String,
        url: String,
        username: String = "",
        password: String = "",
        drop: Boolean = false
    ) {
        val config = HikariConfig().apply {
            driverClassName = driver
            jdbcUrl = url
            this.username = username
            this.password = password
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        val ds = HikariDataSource(config)
        Database.connect(ds)

        if (drop) {
            transaction {
                SchemaUtils.drop(ResourceTable)
            }
        }

        transaction {
            SchemaUtils.createMissingTablesAndColumns(ResourceTable)
        }
    }

    suspend fun <T> query(
        block: suspend () -> T
    ): T = newSuspendedTransaction { block() }
}