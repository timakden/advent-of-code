import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    id("com.github.ben-manes.versions") version "0.43.0"
    kotlin("jvm") version "1.7.20"
}

group = "ru.timakden"
version = "1.0"

repositories {
    mavenCentral()
}

val kotestVersion = "5.5.2"

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.google.code.gson:gson:2.10")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
