val ktorVersion: String by project
val kotlinVersion: String by project
val koinVersion: String by project
val exposedVersion: String by project
val hikariVersion: String by project
val mysqlVersion: String by project
val h2Version: String by project
val flywayVersion: String by project

dependencies {
    implementation(project(":usecases"))
    implementation(project(":domain"))

    api("io.insert-koin", "koin-core", koinVersion)

    implementation("io.ktor","ktor-jackson", ktorVersion)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-java-time", exposedVersion)
    implementation("com.zaxxer", "HikariCP", hikariVersion)
    implementation("mysql:mysql-connector-java:$mysqlVersion")
    implementation("com.h2database", "h2", h2Version)
    implementation("io.insert-koin", "koin-ktor", koinVersion)
    implementation(kotlin("reflect", kotlinVersion))
}