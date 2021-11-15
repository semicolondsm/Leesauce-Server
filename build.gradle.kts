import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val exposedVersion: String by project
val logbackVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.6.0-RC"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

application {
    mainClass.set("server.ServerKt.main")
}

allprojects {
    apply(plugin = "kotlin")

    group = "com.semicolonDSM"
    version = "0.0.1"

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_16.toString()
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-host-common:$ktorVersion")
        implementation("io.ktor:ktor-serialization:$ktorVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")
        implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
        testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    }
}

project.setProperty("mainClassName", "server.ServerKt.main")

tasks {
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "server.ServerKt.main"))
        }
    }
}