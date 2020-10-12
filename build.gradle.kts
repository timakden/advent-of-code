import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    id("com.github.ben-manes.versions") version "0.33.0"
    kotlin("jvm") version "1.4.10"
}

group = "ru.timakden"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

val kotestVersion = "4.3.0"

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
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
