import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21

val arrowVersion: String by project
val coroutinesVersion: String by project
val kotestVersion: String by project
val kotlinVersion: String by project
val serializationVersion: String by project

plugins {
    idea
    id("com.github.ben-manes.versions") version "0.50.0"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
}

group = "ru.timakden"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    implementation("io.arrow-kt:arrow-core:$arrowVersion")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    compileKotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JVM_21)
        }
    }
    test {
        useJUnitPlatform()
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
