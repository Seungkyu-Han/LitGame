plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //module
    implementation(project(":king:king-core"))

    //spring
    implementation("org.springframework.boot:spring-boot-starter-web")

    //websocket
    implementation ("org.springframework.boot:spring-boot-starter-websocket")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}