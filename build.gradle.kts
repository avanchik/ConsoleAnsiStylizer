plugins {
    kotlin("jvm") version "1.9.22"
    application
    `maven-publish`
}

group = "avanchik"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

val publicationName = "mavenKotlin"

publishing {
    publications {
        create<MavenPublication>(publicationName) {
            artifactId = "ConsoleAnsiStylizer"
            from(components["java"])
            groupId = "com.github.avanchik"
            version = "0.0.1"
        }
    }
}