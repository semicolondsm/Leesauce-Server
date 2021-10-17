val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
}

application {
    mainClass.set("server.ServerKt.module")
}

allprojects {
    apply(plugin = "kotlin")

    group = "com.semicolonDSM"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-host-common:$ktorVersion")
        implementation("io.ktor:ktor-serialization:$ktorVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
        testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    }
}