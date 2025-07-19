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

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")

    //lombok
    implementation("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}