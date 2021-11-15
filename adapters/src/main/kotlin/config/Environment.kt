package config

import io.ktor.application.*
import repository.DatabaseFactory
import usecase.Environment

data class Config(
    val development: Boolean,
    val jdbc: JDBC?,
    val dropDB: Boolean,
    val baseUrl: String,
    val uploadDir: String
) {
    init {
        DatabaseFactory.init(jdbc!!.driver, jdbc.url, jdbc.username, jdbc.password, dropDB)
    }

    fun toEnvironment(): Environment = Environment(this.baseUrl, this.uploadDir)
}

data class JDBC(
    val driver: String,
    val url: String,
    val username: String,
    val password: String
)

fun Application.config() = environment.config.run {
    Config(
        development = property("ktor.development").getString().toBoolean(),
        jdbc = JDBC(
            driver = property("database.jdbc.driver").getString(),
            url = property("database.jdbc.url").getString(),
            username = property("database.jdbc.username").getString(),
            password = property("database.jdbc.password").getString(),
        ),
        dropDB = property("database.drop").getString().toBoolean(),
        baseUrl = property("server.baseUrl").getString(),
        uploadDir = property("file.uploadDir").getString()
    )
}