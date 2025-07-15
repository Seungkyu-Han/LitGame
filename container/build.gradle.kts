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
    implementation(project(":king:king-presentation"))

    //spring
    implementation("org.springframework.boot:spring-boot-starter-web")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}