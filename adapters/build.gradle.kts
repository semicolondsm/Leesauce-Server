val kotlinVersion: String by project
val koinVersion: String by project

dependencies {
    api("io.insert-koin", "koin-core", koinVersion)
    implementation("io.insert-koin", "koin-ktor", koinVersion)
    implementation(kotlin("reflect", kotlinVersion))
}