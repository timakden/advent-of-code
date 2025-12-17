import org.gradle.api.JavaVersion.VERSION_25
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25

plugins {
    idea
    alias(libs.plugins.versions)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
}

group = "ru.timakden"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.arrow)
    implementation(libs.jetbrains.annotations)

    ksp(libs.arrow.optics.ksp)

    testImplementation(libs.bundles.kotest)
}

kotlin {
    jvmToolchain(25)
}

java {
    toolchain {
        targetCompatibility = VERSION_25
    }
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(JVM_25)
        }
    }
    test {
        useJUnitPlatform()
    }
    wrapper {
        gradleVersion = "9.2.1"
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}