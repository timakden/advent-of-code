import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "1.3.71"
    id("com.github.ben-manes.versions") version "0.28.0"
}

group = "ru.timakden"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

val slf4jVersion = "1.7.30"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
