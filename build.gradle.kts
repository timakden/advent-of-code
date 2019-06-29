import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "1.3.40"
    id("com.github.ben-manes.versions") version "0.21.0"
}

group = "ru.timakden"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

val slf4jVersion = "1.7.26"
val arrowVersion = "0.9.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("io.arrow-kt:arrow-core-data:$arrowVersion")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
