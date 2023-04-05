import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    idea
    id("com.github.ben-manes.versions") version "0.46.0"
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
}

group = "ru.timakden"
version = "1.0"

repositories {
    mavenCentral()
}

val kotestVersion = "5.5.5"

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.arrow-kt:arrow-core:1.1.5")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
}

kotlin {
    jvmToolchain(19)
}

tasks {
    compileKotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_19)
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
