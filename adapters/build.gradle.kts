val kotlinVersion: String by project
val koinVersion: String by project

dependencies {
    api("io.insert-koin", "koin-core", koinVersion)
    implementation(kotlin("reflect", kotlinVersion))
}