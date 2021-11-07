package config

import io.ktor.application.*

data class Config(
    val development: Boolean,
    val jdbc: JDBC?,
    val dropDB: Boolean,
    val uploadDir: String
)

data class JDBC(
    val driver: String,
    val url: String,
    val schema: String,
    val username: String,
    val password: String
)

fun Application.config() = environment.config.run {
    Config(
        development = property("ktor.development").getString().toBoolean(),
        jdbc = JDBC(
            driver = property("database.jdbc.driver").getString(),
            url = property("database.jdbc.url").getString(),
            schema = property("database.jdbc.schema").getString(),
            username = property("database.jdbc.username").getString(),
            password = property("database.jdbc.password").getString(),
        ),
        dropDB = property("database.drop").getString().toBoolean(),
        uploadDir = property("file.uploadDir").getString()
    )
}