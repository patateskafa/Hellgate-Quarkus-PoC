package team.starfish.hellgate.poc

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import team.starfish.hellgate.poc.model.entity.PspRequests

fun setupDatabase(): Database {
    enableDatabaseLogIfDevMode()
    val db = Database.connect(hikari())
    transaction() {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(PspRequests)
    }
    return db
}

private fun hikari(): HikariDataSource {

    val config = HikariConfig()
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
    config.username = "postgres"
    config.password = "postgres"
    config.isAutoCommit = false
    config.maximumPoolSize = 3
    config.transactionIsolation = "TRANSACTION_SERIALIZABLE"

    config.validate()
    return HikariDataSource(config)
}