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

    //spring
    implementation("org.springframework:spring-context") // 또는 6.x.x

    //websocket
    implementation ("org.springframework.boot:spring-boot-starter-websocket")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}